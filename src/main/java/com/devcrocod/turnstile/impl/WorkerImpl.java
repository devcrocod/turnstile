package com.devcrocod.turnstile.impl;

import com.devcrocod.turnstile.inter.Worker;

import java.util.HashMap;
import java.util.Map;

public class WorkerImpl implements Worker {
    private final Map<String, String> result = new HashMap<>();

    @Override
    public Map<String, String> getResult(String day) {
        if (day.equals("All"))
            return result;
        else {
            String str;
            if (day.matches("\\d{2}-\\d{2}-\\d{4}")) {
                str = (result.get(day) != null) ? result.get(day) : "Нет данных за этот день";
            } else
                str = "Неккоректный ввод";
            Map<String, String> map = new HashMap<>();
            map.put(day, str);
            return map;
        }
    }

    @Override
    public void setResult(String day, String result) {
        this.result.put(day, result);
    }
}
