package com.miu.lab2b;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class PAMSApp {
    public static void main(String[] args) {
        List<Patient> patients = loadData();

        // Sort by Age DESC
        List<Map<String, Object>> enriched = patients.stream()
                .sorted(Comparator.comparingInt(Patient::getAge).reversed())
                .map(p -> {
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("id", p.getPatientId());
                    map.put("firstName", p.getFirstName());
                    map.put("lastName", p.getLastName());
                    map.put("phone", p.getPhone());
                    map.put("email", p.getEmail());
                    map.put("address", p.getAddress());
                    map.put("dob", p.getDob().toString());
                    map.put("age", p.getAge());
                    return map;
                })
                .collect(Collectors.toList());

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(enriched);

        // Write to file
        try (FileWriter writer = new FileWriter("patients.json")) {
            writer.write(jsonOutput);
            System.out.println("Patients JSON written to patients.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Patient> loadData() {
        List<Patient> list = new ArrayList<>();
        list.add(new Patient(1, "Daniel", "Agar", "(641) 123-0009", "dagar@m.as", "1 N Street", LocalDate.of(1987,1,19)));
        list.add(new Patient(2, "Ana", "Smith", null, "amsith@te.edu", null, LocalDate.of(1948,12,5)));
        list.add(new Patient(3, "Marcus", "Garvey", "(123) 292-0018", null, "4 East Ave", LocalDate.of(2001,9,18)));
        list.add(new Patient(4, "Jeff", "Goldbloom", "(999) 165-1192", "jgold@es.co.za", null, LocalDate.of(1995,2,28)));
        list.add(new Patient(5, "Mary", "Washington", null, null, "30 W Burlington", LocalDate.of(1932,5,31)));
        return list;
    }
}
