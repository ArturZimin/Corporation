package by.itstep.zimin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static File file = new File("fileEmployees.txt" + File.separator);

    public static ArrayList<Employee> listEmployee = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        Employee employee1 = new Employee("Dan", "Panda", 31, 2, "manager");
        Employee employee2 = new Employee("Mik", "Pandadoc", 29, 4, "director");
        Employee employee3 = new Employee("Denver", "Hant", 23, 3, "developer");
        Employee employee4 = new Employee("Victor", "Babariko", 24, 5, "director");
        Employee employee5 = new Employee("Svetlana", "Tihanowskaya", 24, 12, "President");
        Employee employee6 = new Employee("Artem", "Panda", 28, 9, "teacher");
        Employee employee7 = new Employee("Sergey", "Tihanovskiy", 28, 6, "project manager");
        Employee employee8 = new Employee("Tatjana", "Avseenko", 37, 7, "sales manager");
        Employee employee9 = new Employee("Kristina", "Rynda", 37, 13, "Architect");
        Employee employee10 = new Employee("Karina", "Okulco", 35, 3, "Tech Lead");
        Employee employee11 = new Employee("Andrey", "Panas", 33, 4, "Team Lead");
        Employee employee12 = new Employee("Olga", "Baydak", 31, 2, "dDevOps");

        listEmployee.add(employee1);
        listEmployee.add(employee2);
        listEmployee.add(employee3);
        listEmployee.add(employee4);
        listEmployee.add(employee5);
        listEmployee.add(employee6);
        listEmployee.add(employee7);
        listEmployee.add(employee8);
        listEmployee.add(employee9);
        listEmployee.add(employee10);
        listEmployee.add(employee11);
        listEmployee.add(employee12);

        Employees employees = new Employees(listEmployee);

        writeToFileObj(employees);

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        Employees employeesRead = null;
        try {
            employeesRead = (Employees) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            objectInputStream.close();
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        showMenu(true);
        String readChoose = br.readLine();
        String template = "[1,2,3,4,5,6,7]";
        if (readChoose.matches(template)) {
            int choose = (int) Integer.parseInt(readChoose);
            boolean run = true;
            while (run) {
                switch (choose) {
                    case 1:
                        System.out.println("Enter the name of the new Employee");
                        String readName = br.readLine();
                        System.out.println("Enter the surname of the new Employee");
                        String readSurname = br.readLine();
                        System.out.println("Enter the age of the new Employee");
                        int readAge = Integer.parseInt(br.readLine());
                        System.out.println("Enter the work experience of the new Employee");
                        int readWorkExperience = Integer.parseInt(br.readLine());
                        System.out.println("Enter the position of the new Employee");
                        String readPosition = br.readLine();

                        addEmployee(new Employee(readName, readSurname, readAge, readWorkExperience, readPosition));
                        writeToFileObj(employees);
                        break;
                    case 2:
                        employees.changeDataOfEmployee();
                        break;
                    case 3:
                        System.out.println("Enter the surname of the Employee to delete");
                        String readSur = br.readLine();
                        employees.deleteEmployee(readSur);
                        break;
                    case 4:
                        System.out.println("Enter the surname of the Employee for searching");
                        employees.lookForEmployeeBySurname(br.readLine());
                        break;
                    case 5:
                        System.out.println("If you want to look for by age? Enter - age \n" +
                                "If you want to look for by first word of surname? Enter - word");
                        String readAnswer = br.readLine();
                        if (readAnswer.equals("age")) {
                            System.out.println("Enter the age of Employee for looking for \n" +
                                    "Enter the path of new file for saved ");

                            System.out.println(employees.searchForEmployeeByAgeOrFirstWordSurname(Integer.parseInt(br.readLine()), br.readLine()));
                        } else {
                            System.out.println("Enter the first word of the surname for looking for the Employees\n" +
                                    "Enter the path of new file for saved ");
                            System.out.println(employees.searchForEmployeeByAgeOrFirstWordSurname(br.readLine(), br.readLine()));

                        }
                        break;
                    case 6:
                        showAllTheEmployees();
                        break;
                    case 7:
                        System.out.println("Everything will be save automatically , Goodbye!");
                        writeToFileObj(employeesRead);
                        run = false;
                        br.close();
                        break;

                }
                if (choose == 1 || choose == 2 || choose == 3) {
                    System.out.println(" If you want to save all the changes that enter \" y \"");

                    String answerOfQuestion = null;
                    try {
                        answerOfQuestion = br.readLine();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if ("y".equals(answerOfQuestion)) {
                        writeToFileObj(employeesRead);
                        System.out.println("Everything the changes were saved!");
                    }
                }

                if (choose == 7) {
                    break;
                }

                showMenu(false);
                try {
                    choose = (int) Integer.parseInt(br.readLine());
                } catch (NumberFormatException n) {
                    System.out.println(" The symbol entered wrong!");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }


    }

    private static void showMenu(boolean flag) {
        if (flag) {
            System.out.println("Welcome to the Corporation information system!\n" +
                    " Menu: \n" +
                    "1 - add new Employee\n" +
                    "2 - change data Employee\n" +
                    "3 - delete Employee\n" +
                    "4 - look for Employee by surname\n" +
                    "5 - show information about Employee by age or the first word of surname\n" +
                    "6 - show all the Employees\n" +
                    "7 - exit. ");
        } else {
            System.out.println(
                    " Menu: \n" +
                            "1 - add new Employee\n" +
                            "2 - change data Employee\n" +
                            "3 - delete Employee\n" +
                            "4 - look for Employee by surname\n" +
                            "5 - show information about Employee by age or the first word of surname\n" +
                            "6 - show all the Employees\n" +
                            "7 - exit. ");

        }
    }


    public static void addEmployee(Employee employee) {
        listEmployee.add(employee);
    }

    public static void showAllTheEmployees() {
        listEmployee.stream()
                .sorted()
                .forEach(x -> System.out.println(x));
    }

    private static void writeToFileObj(Employees e) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
        objectOutputStream.writeObject(e);
        objectOutputStream.close();
    }


}

/**
 * Задание 5
 * Напишите информационную систему "Корпорация".
 * Программа должна обеспечивать:
 * ■ ввод данных;
 * ■ редактирование данных сотрудника корпорации;
 * ■ удаление сотрудника корпорации;
 * ■ поиск сотрудника корпорации по фамилии;
 * ■ вывод информации обо всех сотрудниках, указанного возраста, или фамилия которых начинается на
 * указанную букву.
 * Организуйте возможность сохранения найденной
 * информации в файл.
 * Также весь список сотрудников корпорации сохраняется
 * в файл (при выходе из программы автоматически, в процессе исполнения программы по команде пользователя).
 * При старте программы происходит загрузка списка
 * сотрудников корпорации из указанного пользователем
 * файла.
 * Практические задания
 */
