package org.pz3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            try {
                System.out.println("\nДоступні операції:");
                System.out.println("1. Додавання (+)");
                System.out.println("2. Віднімання (-)");
                System.out.println("3. Множення (*)");
                System.out.println("4. Ділення (/)");
                System.out.println("5. Квадратний корінь (sqrt)");
                System.out.println("6. Степінь (pow)");
                System.out.println("7. Факторіал (!)");
                System.out.println("0. Вихід");

                System.out.print("\nОберіть операцію: ");
                String operation = scanner.nextLine().trim();

                if (operation.equals("0")) {
                    System.out.println("\n✓ До свидання!");
                    running = false;
                    continue;
                }

                try {
                    switch (operation) {
                        case "1":
                        case "+":
                            System.out.print("Введіть перше число: ");
                            double a1 = parseDouble(scanner.nextLine());
                            System.out.print("Введіть друге число: ");
                            double b1 = parseDouble(scanner.nextLine());
                            double result1 = calculator.add(a1, b1);
                            System.out.println("✓ Результат: " + a1 + " + " + b1 + " = " + result1);
                            break;

                        case "2":
                        case "-":
                            System.out.print("Введіть перше число: ");
                            double a2 = parseDouble(scanner.nextLine());
                            System.out.print("Введіть друге число: ");
                            double b2 = parseDouble(scanner.nextLine());
                            double result2 = calculator.subtract(a2, b2);
                            System.out.println("✓ Результат: " + a2 + " - " + b2 + " = " + result2);
                            break;

                        case "3":
                        case "*":
                            System.out.print("Введіть перше число: ");
                            double a3 = parseDouble(scanner.nextLine());
                            System.out.print("Введіть друге число: ");
                            double b3 = parseDouble(scanner.nextLine());
                            double result3 = calculator.multiply(a3, b3);
                            System.out.println("✓ Результат: " + a3 + " * " + b3 + " = " + result3);
                            break;

                        case "4":
                        case "/":
                            System.out.print("Введіть ділене: ");
                            double a4 = parseDouble(scanner.nextLine());
                            System.out.print("Введіть дільник: ");
                            double b4 = parseDouble(scanner.nextLine());
                            double result4 = calculator.divide(a4, b4);
                            System.out.println("✓ Результат: " + a4 + " / " + b4 + " = " + result4);
                            break;

                        case "5":
                        case "sqrt":
                            System.out.print("Введіть число: ");
                            double a5 = parseDouble(scanner.nextLine());
                            double result5 = calculator.squareRoot(a5);
                            System.out.println("✓ Результат: √" + a5 + " = " + result5);
                            break;

                        case "6":
                        case "pow":
                            System.out.print("Введіть основу: ");
                            double base = parseDouble(scanner.nextLine());
                            System.out.print("Введіть степінь: ");
                            double exponent = parseDouble(scanner.nextLine());
                            double result6 = calculator.power(base, exponent);
                            System.out.println("✓ Результат: " + base + "^" + exponent + " = " + result6);
                            break;

                        case "7":
                        case "!":
                            System.out.print("Введіть число: ");
                            double a7 = parseDouble(scanner.nextLine());
                            double result7 = calculator.factorial(a7);
                            System.out.println("✓ Результат: " + (int) a7 + "! = " + (long) result7);
                            break;

                        default:
                            System.out.println("✗ Невідома операція! Спробуйте ще раз.");
                    }
                } catch (ArithmeticException e) {
                    System.out.println("✗ Математична помилка: " + e.getMessage());
                } catch (InvalidInputException e) {
                    System.out.println("✗ Помилка вхідних даних: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("✗ Помилка: введіть коректне число!");
                }

            } finally {
                System.out.println("\n" + "-".repeat(56));
            }
        }
        scanner.close();
    }

    private static double parseDouble(String input) throws NumberFormatException {
        try {
            return Double.parseDouble(input.trim());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("\"" + input + "\" не є числом!");
        }
    }
}

