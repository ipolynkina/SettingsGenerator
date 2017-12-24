package ru.ipolynkina.beans;

import java.util.List;

public class Coefficients {

    private List<String> coefficients;

    public Coefficients(List<String> coefficients) {
        this.coefficients = coefficients;
    }

    public List<String> getCoefficients() {
        return coefficients;
    }

    public String getCoefficientByIndex(int index) {
        if(index >= coefficients.size()) throw new ArrayIndexOutOfBoundsException();
        return coefficients.get(index);
    }
}
