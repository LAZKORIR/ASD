package com.miu.lab2a;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeApp {
    public static void main(String[] args) {

        List<Employee> employees = loadData();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(java.time.LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();


        // 1. Print all employees
        System.out.println("=== All Employees ===");
        List<Employee> sortedEmployees = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getYearlySalary).reversed()
                        .thenComparing(Employee::getLastName))
                .collect(Collectors.toList());
        System.out.println(gson.toJson(sortedEmployees));

        // 2. Quarterly Upcoming Enrollees
        System.out.println("\n=== Quarterly Upcoming Enrollees ===");
        LocalDate now = LocalDate.now();
        int currentQuarter = (now.getMonthValue() - 1) / 3 + 1;   // 1-4
        int nextQuarter = currentQuarter == 4 ? 1 : currentQuarter + 1;
        int nextQuarterYear = currentQuarter == 4 ? now.getYear() + 1 : now.getYear();
        int nextQuarterStartMonth = (nextQuarter - 1) * 3 + 1;

        LocalDate firstDayNextQuarter = LocalDate.of(nextQuarterYear, nextQuarterStartMonth, 1);
        LocalDate lastDayNextQuarter = firstDayNextQuarter.plusMonths(3).minusDays(1);


        List<Employee> upcoming = employees.stream()
                .filter(e -> !e.isEnrolled())
                .filter(e -> {
                    LocalDate eligibilityDate = e.getEmploymentDate().plusYears(3);
                    return (eligibilityDate.isEqual(firstDayNextQuarter) || eligibilityDate.isAfter(firstDayNextQuarter))
                            && (eligibilityDate.isBefore(lastDayNextQuarter) || eligibilityDate.isEqual(lastDayNextQuarter));
                })
                .sorted(Comparator.comparing(Employee::getEmploymentDate).reversed())
                .collect(Collectors.toList());

        System.out.println(gson.toJson(upcoming));
    }

    private static List<Employee> loadData() {
        List<Employee> employees = new ArrayList<>();

        Employee e1 = new Employee(1, "Daniel", "Agar", LocalDate.of(2023, 1, 17), 105945.50);
        e1.setPensionPlan(new PensionPlan("EX1089", null, 100.00));
        employees.add(e1);

        Employee e2 = new Employee(2, "Benard", "Shaw", LocalDate.of(2022, 9, 3), 197750.00);
        employees.add(e2);

        Employee e3 = new Employee(3, "Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75);
        e3.setPensionPlan(new PensionPlan("SM2307", LocalDate.of(2017, 5, 17), 1555.50));
        employees.add(e3);

        employees.add(new Employee(4, "Wesley", "Schneider", LocalDate.of(2023, 7, 21), 74500.00));
        employees.add(new Employee(5, "Anna", "Wiltord", LocalDate.of(2023, 3, 15), 85750.00));
        employees.add(new Employee(6, "Yosef", "Tesfalem", LocalDate.of(2024, 10, 31), 100000.00));

        return employees;
    }
}
