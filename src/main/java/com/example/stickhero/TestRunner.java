package com.example.stickhero;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner{
    public static void main(String[] args) {
        Result r=JUnitCore.runClasses(Testing.class);
        for(Failure f:r.getFailures())
        {
            System.out.println(f);
        }
        System.out.println(r.wasSuccessful());
    }
}
