package com.devcrocod.turnstile;

import com.devcrocod.turnstile.data.DiscreteTime;
import com.devcrocod.turnstile.data.WorkerImpl;
import com.devcrocod.turnstile.impl.CountingTimeImpl;
import com.devcrocod.turnstile.impl.WorkWithFilesImpl;
import com.devcrocod.turnstile.inter.CountingTime;
import com.devcrocod.turnstile.inter.WorkWithFiles;
import com.devcrocod.turnstile.inter.Worker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) {
        WorkWithFiles wwf = new WorkWithFilesImpl();
        CountingTime countingTime = new CountingTimeImpl();
        Worker worker = new WorkerImpl();
        try {
            List<File> fileList = Files.walk(Paths.get("src/main/resources"))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
            for (File file : fileList) {
                List<DiscreteTime> employeeDay = new ArrayList<>();
                wwf.readFile(file.getPath(), employeeDay);
                worker.setResult(file.getName().substring(0, file.getName().length() - 4), countingTime.counting(employeeDay));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        String day = scanner.next();
        try {
            System.out.println(worker.getResult(day));
        } catch (IllegalArgumentException e) {
            Logger.getLogger(Main.class.getName()).log(new LogRecord(Level.WARNING, "Была передана некорректная дата"));
        }
    }
}
