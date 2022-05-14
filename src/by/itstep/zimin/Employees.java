package by.itstep.zimin;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Employees implements Serializable {

    private ArrayList<Employee> listEmployee;


    public Employees(ArrayList<Employee> list) {
        this.listEmployee = list;
    }

    public Employees() {
    }


    public void changeDataOfEmployee() throws IOException {

        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter surname Employee for change ");
        String surname = br1.readLine();
        System.out.println(lookForEmployeeBySurname(surname).toString());
        System.out.println("choose what to change:\n" +
                "1 - name\n" +
                "2 - surname\n" +
                "3 - age\n" +
                "4 - work experience\n" +
                "5 - position.");

        String choose = br1.readLine();

        String template = "[1,2,3,4,5]";

        Integer parseToInt;
        if (choose.matches(template)) {
            parseToInt = Integer.parseInt(choose);

            switch (parseToInt) {
                case 1:
                    System.out.println("Enter a new name ");
                    String newName = br1.readLine();
                    for (Employee e : listEmployee) {
                        if (e.getSurname().equals(surname)) {
                            e.setName(newName);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter a new surname ");
                    String newSurname = br1.readLine();
                    for (Employee e : listEmployee) {
                        if (e.getSurname().equals(surname)) {
                            e.setSurname(newSurname);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Enter a new age ");
                    int newAge = Integer.parseInt(br1.readLine());
                    for (Employee e : listEmployee) {
                        if (e.getSurname().equals(surname)) {
                            e.setAge(newAge);
                        }
                    }
                    break;
                case 4:
                    System.out.println("Enter a new work experience ");
                    int newWorkExperience = 0;
                    try {
                        newWorkExperience = (int) Integer.parseInt(br1.readLine());
                        for (Employee e : listEmployee) {
                            if (e.getSurname().equals(surname)) {
                                e.setWorkExperience(newWorkExperience);
                            }
                        }
                    } catch (NumberFormatException n) {
                        n.printStackTrace();
                    }

                    break;
                case 5:
                    System.out.println("Enter a new position ");
                    String newPosition = br1.readLine();
                    for (Employee e : listEmployee) {
                        if (e.getSurname().equals(surname)) {
                            e.setPosition(newPosition);
                        }
                    }
                    break;
            }
        } else {
            br1.close();
            throw new IllegalArgumentException("The number entered wrong ! ");
        }
    }


    public boolean deleteEmployee(String surname) {
        boolean isDeleted = this.listEmployee.removeIf(x -> x.getSurname().equals(surname));
        if (isDeleted == false) {
            System.out.println("The Employee not found! ");
            return isDeleted;
        } else {
            System.out.println("The Employee was deleted! ");

            return isDeleted;
        }
    }

    public List<Employee> lookForEmployeeBySurname(String surname) {
        List<Employee> list = this.listEmployee.stream()
                .filter(x -> x.getSurname().equals(surname))
                .toList();
        return list;
    }

    public void writeToFile(Object o, String path) throws IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try {
            bufferedWriter.write(o.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bufferedWriter.flush();
            bufferedWriter.close();
        }

    }


    public List<Employee> searchForEmployeeByAgeOrFirstWordSurname(int age, String path) throws IOException {

        List<Employee> list = null;

        if (age != 0) {
            list = this.listEmployee.stream()
                    .filter(x -> x.getAge() == age)
                    .toList();

            writeToFile(list.toString(), path);
            return list;
        }
        writeToFile(list.toString(), path);
        return list;
    }

    public List<Employee> searchForEmployeeByAgeOrFirstWordSurname(String firstWordOfSurname, String path) throws
            IOException {

        List<Employee> list = new ArrayList<>();
        if (firstWordOfSurname != null && path != null) {
            for (int i = 0; i < this.listEmployee.size(); i++) {
                String current = listEmployee.get(i).getSurname();
                if (current.contains(firstWordOfSurname)) {
                    list.add(listEmployee.get(i));
                }
            }
        }
        writeToFile(list.toString(), path);
        return list;
    }


}
