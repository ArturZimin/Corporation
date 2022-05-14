package by.itstep.zimin;

import java.io.Serializable;
import java.util.Objects;

public class Employee implements Comparable<Employee>, Serializable {
    private String name;
    private String surname;
    private int age;
    private int workExperience;
    private String position;

    public Employee(String name, String surname, int age, int workExperience, String position) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.workExperience = workExperience;
        this.position = position;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && workExperience == employee.workExperience && Objects.equals(name, employee.name) && Objects.equals(surname, employee.surname) && Objects.equals(position, employee.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, age, workExperience, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", workExperience=" + workExperience +
                ", position='" + position + '\'' +
                '}';
    }


    @Override
    public int compareTo(Employee o) {
        Employee b=null;
        if (o.getClass()==Employee.class &&o!=null) {
          b = (Employee) this;
            return b.getSurname().compareTo(o.getSurname());
        }else {
            throw new IllegalArgumentException("The argument is wrong!");
        }

    }
}
