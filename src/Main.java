import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>();

        Car car = new Car();
        car.id = 1234;
        car.model = "Toyota Etios";

        employeeList.add(new Employee(1, "Suresh", LocalDate.now(), "Mr", car));
        employeeList.add(new Employee(5, "Ramesh", LocalDate.now(), "Dr", car));

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("employees.dat"))) {
            objectOutputStream.writeObject(employeeList);
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("employees.dat"))) {
            List<Employee> employeeList1 = (List<Employee>) objectInputStream.readObject();

            for (Employee e: employeeList1) {
                System.out.println(e);
            }
        }
    }
}

class Employee implements Serializable {
    private int id;
    private String name;
    private LocalDate dob;
    public String title;
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee(int id, String name, LocalDate dob, String title, Car car) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.title = title;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", title='" + title + '\'' +
                ", car=" + car +
                '}';
    }
}

class Car implements Serializable {
    public int id;
    public String model;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                '}';
    }
}