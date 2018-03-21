package com.devcrocod.turnstile;

import com.devcrocod.turnstile.data.DiscreteTime;
import com.devcrocod.turnstile.impl.CountingTimeImpl;
import com.devcrocod.turnstile.impl.WorkWithFilesImpl;
import com.devcrocod.turnstile.impl.WorkerImpl;
import com.devcrocod.turnstile.inter.CountingTime;
import com.devcrocod.turnstile.inter.WorkWithFiles;
import com.devcrocod.turnstile.inter.Worker;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class DateWithTimeController {

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public ModelAndView tableWorkerDays(ModelMap model,
                                        @RequestParam(value = "day", defaultValue = "All") String day) {
        Worker worker = new WorkerImpl();
        run(worker);
        Map<String, String> mapResult = worker.getResult(day);
        model.put("mapResult", mapResult);
        return new ModelAndView("index", model);
    }

    private void run(Worker worker) {
        WorkWithFiles wwf = new WorkWithFilesImpl();
        CountingTime countingTime = new CountingTimeImpl();

        try {
            List<File> fileList = Files.walk(Paths.get("src/main/resources/static/dates/"))
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
    }
}
