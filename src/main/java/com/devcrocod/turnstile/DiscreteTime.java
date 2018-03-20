package com.devcrocod.turnstile;

public class DiscreteTime {

    private String direct;

    private Long timeMin;

    DiscreteTime(String direct, Long timeMin) {
        this.direct = direct;
        this.timeMin = timeMin;
    }


    DiscreteTime(String[] str) {
        setDirect(str[0]);
        setTimeMin(str[1]);
    }

    public Long getTimeMin() {
        return timeMin;
    }

    public boolean condDirect(String dir) {
        return direct.equals(dir);
    }

    private void setDirect(String direct) {
        this.direct = direct;
    }

    private void setTimeMin(String time) {
        String[] a;
        a = time.split(":");
        timeMin = Long.valueOf(a[0]) * 60 + Long.valueOf(a[1]);
    }

    @Override
    public String toString() {
        return "DiscreteTime{" +
                "direct='" + direct + '\'' +
                ", timeMin=" + timeMin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscreteTime discreteTime = (DiscreteTime) o;

        return direct.equals(discreteTime.direct) && timeMin.equals(discreteTime.timeMin);
    }

    @Override
    public int hashCode() {
        int result = direct.hashCode();
        result = 31 * result + timeMin.hashCode();
        return result;
    }
}
