package com.devcrocod.turnstile;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String filename = "src/main/resources/18-03-2018.csv";
        List<DiscreteTime> employeeDay = new ArrayList<>();
        WorkWithFiles wwf = new WorkWithFilesImpl();
        wwf.readFile(filename, employeeDay);
        CountingTime countingTime = new CountingTimeImpl();
        System.out.println(countingTime.counting(employeeDay));


        String s = " s";
    }
}
