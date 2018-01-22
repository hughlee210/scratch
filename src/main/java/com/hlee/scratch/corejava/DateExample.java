package com.hlee.scratch.corejava;

import java.util.Calendar;
import java.util.Date;

public class DateExample {

    public static void main(String[] args) {
        testDate();
    }

    private static void testDate() {

        Date calDate = Calendar.getInstance().getTime();
        Date date = new Date();
        long time = System.currentTimeMillis();

        // -Duser.timezone=UTC is set in Run Configuration
        System.out.println("    cal date in millisec: " + calDate.getTime() + ", toString: " + calDate);
        System.out.println("        date in millisec: " + date.getTime() + ", toString: " + date);
        System.out.println("current time in millisec: " + time);

    }
}
