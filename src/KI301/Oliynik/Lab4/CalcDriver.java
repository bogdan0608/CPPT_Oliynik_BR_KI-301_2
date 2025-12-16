package KI301.Oliynik.Lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Клас-драйвер <code>CalcDriver</code> демонструє роботу
 * класу {@link ExpressionCalculator}.
 *
 * Програма:
 * <ul>
 *     <li>зчитує значення x з консолі;</li>
 *     <li>обчислює y = ctg(x) / (sin(2x) + 4cos(x));</li>
 *     <li>записує результат у файл;</li>
 *     <li>обробляє можливі помилки за допомогою механізму виключень.</li>
 * </ul>
 */
public class CalcDriver {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ExpressionCalculator calculator = new ExpressionCalculator();

        out.println("Обчислення виразу y = ctg(x) / (sin(2x) + 4cos(x))");
        out.print("Введіть x (у радіанах): ");

        double x;

        try {
            x = scanner.nextDouble();
        } catch (InputMismatchException e) {
            out.println("Помилка вводу: потрібно ввести дійсне число.");
            return;
        }

        double y;

        try {
            y = calculator.calculate(x);
        } catch (CalculationException e) {
            out.println("Помилка обчислення: " + e.getMessage());
            // Спробуємо записати повідомлення про помилку у файл
            writeResultToFile("Lab4_Oliinyk_Result.txt",
                    "x = " + x + "\n" + "Помилка: " + e.getMessage());
            return;
        }

        out.println("Результат: y = " + y);

        // Запис результату у файл
        StringBuilder sb = new StringBuilder();
        sb.append("Результат обчислення виразу y = ctg(x) / (sin(2x) + 4cos(x))\n");
        sb.append("x = ").append(x).append("\n");
        sb.append("y = ").append(y).append("\n");

        writeResultToFile("Lab4_Oliinyk_Result.txt", sb.toString());
    }

    /**
     * Записати текстовий результат у вказаний файл.
     *
     * @param fileName назва файлу
     * @param content  вміст для запису
     */
    private static void writeResultToFile(String fileName, String content) {
        try (PrintWriter fout = new PrintWriter(new File(fileName))) {
            fout.println(content);
            out.println("Результат записано у файл: " + fileName);
        } catch (FileNotFoundException e) {
            out.println("Не вдалося відкрити файл для запису: " + e.getMessage());
        }
    }
}