package KI301.Oliynik.Lab4;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Тест-драйвер для перевірки ExpressionCalculatorIO.
 */
public class TestIO {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpressionCalculatorIO io = new ExpressionCalculatorIO();
        out.println("Обчислення виразу y = ctg(x) / (sin(2x) + 4cos(x))");
        System.out.print("Введіть x: ");
        double x = sc.nextDouble();

        double y;
        try {
            y = io.calculate(x);
        } catch (CalculationException e) {
            System.out.println("Помилка обчислення: " + e.getMessage());
            return;
        }

        System.out.println("y = " + y);

        try {
            io.writeText(x, y, "result.txt");
            io.writeBinary(x, y, "result.bin");

            System.out.println("Записано у файли result.txt і result.bin");

            double[] t = io.readText("result.txt");
            double[] b = io.readBinary("result.bin");

            System.out.println("З текстового файла: x=" + t[0] + ", y=" + t[1]);
            System.out.println("З двійкового файла: x=" + b[0] + ", y=" + b[1]);

        } catch (IOException e) {
            System.out.println("Помилка I/O: " + e.getMessage());
        }
    }
}