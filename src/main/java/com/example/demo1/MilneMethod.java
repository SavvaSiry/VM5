package com.example.demo1;

import javafx.util.Pair;

import java.util.ArrayList;

public class MilneMethod {
    IFunc func;
    double step;
    private final ArrayList<Pair<Double, Double>> values;

    public MilneMethod(IFunc func, ArrayList<Pair<Double, Double>> answers, double step) {
        this.func = func;
        values = answers;
        this.step = step;
        for (int i = 4; i < values.size(); i++) {
            System.out.println(i + ". x = " + values.get(i).getKey() + "; y = " + values.get(i).getValue());
            predictAndCorrect(i);
            System.out.println(i + ". x = " + values.get(i).getKey() + "; y = " + values.get(i).getValue());
            System.out.println("---------");
        }
    }

    private void predictAndCorrect (int i) {
        double f_3, f_2, f_1, f;
        f_3 = getResultOfFunction(values.get(i-3).getKey(), values.get(i-3).getValue());
        f_2 = getResultOfFunction(values.get(i-2).getKey(), values.get(i-2).getValue());
        f_1 = getResultOfFunction(values.get(i-1).getKey(), values.get(i-1).getValue());
        //predictor
        f = getResultOfFunction(values.get(i-1).getKey()+step, values.get(i-4).getValue() + 4*step*(2*f_3+f_2+2*f_1)/3);
        //corrector
        values.set(i, new Pair<>(values.get(i-1).getKey()+step, values.get(i-2).getValue() + step*(f+4*f_1+f_2)/3));
    }


    private double getResultOfFunction (double xi, double yi) {
        return func.solve(xi, yi);
    }

    public ArrayList<Pair<Double, Double>> getValues() {
        return values;
    }
}
