package com.devcrocod.turnstile.inter;

import java.util.Map;

public interface Worker {

    Map<String, String> getResult(String day);

    void setResult(String day, String result);
}
