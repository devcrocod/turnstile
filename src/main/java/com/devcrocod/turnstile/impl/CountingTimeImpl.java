package com.devcrocod.turnstile.impl;

import com.devcrocod.turnstile.data.DiscreteTime;
import com.devcrocod.turnstile.inter.CountingTime;

import java.util.List;

public class CountingTimeImpl implements CountingTime {

    @Override
    public String counting(List<DiscreteTime> employeeDay) {
        long time = 0L;
        sorting(employeeDay);
        for (int i = 0; i < employeeDay.size() - 1; i += 2) {
            time += employeeDay.get(i + 1).getTimeMin() - employeeDay.get(i).getTimeMin();
        }
        return (time / 60) + ":" + (time % 60 / 10) + (time % 60 % 10);
    }

    private void sorting(List<DiscreteTime> employeeDay) {
        for (int i = 1; i < employeeDay.size() - 1; i++) {
            if (employeeDay.get(i - 1).condDirect("вход") && !employeeDay.get(i).condDirect("выход")) {
                while (i < employeeDay.size() && employeeDay.get(i).condDirect("вход")) {
                    employeeDay.remove(i);
                }
                i--;
            }
            if (employeeDay.get(i).condDirect("выход") && !employeeDay.get(i + 1).condDirect("вход")) {
                while (i < employeeDay.size() && employeeDay.get(i).condDirect("выход")) {
                    employeeDay.remove(i);
                    i--;
                }
            }
        }
    }
}
