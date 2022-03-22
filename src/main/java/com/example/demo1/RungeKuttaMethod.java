package com.example.demo1;

import javafx.util.Pair;

import java.util.ArrayList;

public class RungeKuttaMethod {
    IFunc func;
    private final double step;
    private final ArrayList<Pair<Double, Double>> values;

    public RungeKuttaMethod(IFunc func, double startX, double startY, double step, double lastX) {
        this.func = func;
        this.step = step;
        values = new ArrayList<>();
        values.add(new Pair<>(startX, startY));
        double nextY = startY;
        for (double i = startX; i < lastX; i += step) {
            nextY = nextRKValue(i, nextY);
            System.out.println("For value x = " + (i+step) + "; y = " + nextY);
            values.add(new Pair<>(i+step, nextY));
            System.out.println("==========");
        }
    }

    private double nextRKValue(double lastX, double lastY) {
        double k1, k2, k3, k4, deltaValue;
        k1 = getResultOfFunction(lastX, lastY);
        System.out.println("k1 = " + k1);
        k2 = getResultOfFunction(lastX + step/2, lastY + step*k1/2);
        System.out.println("k2 = " + k2);
        k3 = getResultOfFunction(lastX + step/2, lastY + step*k2/2);
        System.out.println("k3 = " + k3);
        k4 = getResultOfFunction(lastX + step, lastY + step*k3);
        System.out.println("k4 = " + k4);
        deltaValue = (step/6)*(k1+2*k2+2*k3+k4);
        return lastY + deltaValue;
    }

    private double getResultOfFunction (double xi, double yi) {
        return func.solve(xi, yi);
    }

    public ArrayList<Pair<Double, Double>> getValues() {
        return values;
    }
}
