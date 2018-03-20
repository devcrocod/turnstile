package com.devcrocod.turnstile.impl;

import com.devcrocod.turnstile.inter.Worker;

import java.util.HashMap;
import java.util.Map;

public class WorkerImpl implements Worker {
    private final Map<String, String> result = new HashMap<>();

    @Override
    public String getResult(String day) {
        if (!day.matches("\\d{2}-\\d{2}-\\d{4}")) throw new IllegalArgumentException();
        return (result.get(day) != null) ? result.get(day) : "Нет данных за этот день";
    }

    @Override
    public void setResult(String day, String result) {
        this.result.put(day, result);
    }
}
