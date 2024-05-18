import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        copyFileRawNio();
    }

    static void copyFileRawNio() {
        //  NIO brings the concept of a channel, which is a duplex communication system
        //  As you read from a channel to a buffer, the data from the source (such as a file)
        //  is read to a buffer. But the source file can be much larger than the buffer provided.
        //  In such cases, the buffer content is say written to the writer channel. To do this,
        //  you need to flip the buffer so that it could be read from the beginning. Once the
        //  writing of this buffer to the writer channel is done, the buffer needs to be cleared.
        //  Again the channel reads data from where it left off to this buffer. This process
        //  continues so long as there is content to be read in the source file. If the EOF is
        //  reached, 0 (in some edge cases) -1 (normally) is returned. Please note that the buffer
        //  may not be read fully in a read cycle. Usually, in a file reading operation, you may
        //  get a full buffer except may be when it is the last read cycle. It is always better
        //  to check the number of bytes read.

        try(FileChannel readerChannel = FileChannel.open(Path.of("earth.png"), StandardOpenOption.READ);
            FileChannel writerChannel = FileChannel.open(Path.of("earth-copy.png"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        ) {
            //  allocate(int) allocates the buffer in the JVM heap
            //  allocateDirect(int) allocates the buffer outside the JVM heap (called direct buffer)
            //  direct buffer works significantly faster for large file IO. However, the allocation
            //  of direct buffer is orders of magnitudes (sometimes >50x) slower compared to JVM allocation.
            //  Generally, for high-performance and frequent IO, the direct buffer is used.
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int totalBytesWritten = 0;

            while (readerChannel.read(buffer) > 0) {
                //  limit of the buffer now becomes the current position
                //  then the current position is set to zero (beginning)
                buffer.flip();

                totalBytesWritten += writerChannel.write(buffer);

                buffer.clear();
            }

            System.out.println(String.format("Written %d bytes in total", totalBytesWritten));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}