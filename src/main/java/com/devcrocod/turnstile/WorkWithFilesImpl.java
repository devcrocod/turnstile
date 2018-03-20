package com.devcrocod.turnstile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class WorkWithFilesImpl implements WorkWithFiles {

    @Override
    public void readFile(String fileName, List<DiscreteTime> employeeDay) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready()) {
                DiscreteTime discreteTime = new DiscreteTime(reader.readLine().split(","));
                employeeDay.add(discreteTime);
            }
            if (!employeeDay.get(0).condDirect("вход")) {
                employeeDay.add(0, new DiscreteTime("вход", 0L));
            }
            if (!employeeDay.get(employeeDay.size() - 1).condDirect("выход")) {
                employeeDay.add(new DiscreteTime("выход", 1439L));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
