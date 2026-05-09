package org.pz3;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе!");
        }
        return a / b;
    }

    public double squareRoot(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Число не може бути від'ємним для обчислення квадратного кореня!");
        }
        return Math.sqrt(a);
    }

    public double power(double base, double exponent) throws InvalidInputException {
        if (base < 0 && exponent % 1 != 0) {
            throw new InvalidInputException("Не можна піднести від'ємне число до дробового степеня!");
        }
        return Math.pow(base, exponent);
    }

    public double factorial(double n) throws InvalidInputException {
        if (n < 0) {
            throw new InvalidInputException("Факторіал від'ємного числа не існує!");
        }
        if (n != Math.floor(n)) {
            throw new InvalidInputException("Факторіал визначений тільки для цілих чисел!");
        }

        int num = (int) n;
        if (num > 20) {
            throw new InvalidInputException("Факторіал занадто великий!");
        }

        long result = 1;
        for (int i = 2; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}

