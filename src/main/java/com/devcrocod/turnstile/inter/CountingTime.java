package com.devcrocod.turnstile.inter;

import com.devcrocod.turnstile.data.DiscreteTime;

import java.util.List;

public interface CountingTime {
    String counting(List<DiscreteTime> employeeDay);
}
