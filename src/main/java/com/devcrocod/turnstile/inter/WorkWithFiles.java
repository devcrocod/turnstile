package com.devcrocod.turnstile.inter;

import com.devcrocod.turnstile.data.DiscreteTime;

import java.util.List;

public interface WorkWithFiles {

    void readFile(String fileName, List<DiscreteTime> employeeDay);
}
