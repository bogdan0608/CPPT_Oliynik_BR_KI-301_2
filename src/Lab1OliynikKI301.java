import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Lab1: Генерація зубчатого масиву (jagged array), який містить лише "заштриховану"
 * область квадратної матриці N×N за варіантом "ромб".
 *
 * <p>Користувач вводить:</p>
 * <ul>
 *   <li>розмір квадратної матриці N</li>
 *   <li>символ-заповнювач (рівно 1 символ)</li>
 * </ul>
 *
 * <p>Програма формує зубчатий масив, де кожен рядок має довжину, що відповідає
 * ширині ромба в цьому рядку (без пробілів поза ромбом), виводить його у консоль
 * та записує у текстовий файл.</p>
 */
public class Lab1OliynikKI301 {

    /** Назва вихідного файлу. */
    private static final String OUTPUT_FILE = "Lab1_output.txt";

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка (не використовуються)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введіть розмір квадратної матриці N (N > 0): ");
        if (!sc.hasNextInt()) {
            System.out.println("Помилка: N має бути цілим числом. Завершення.");
            return;
        }

        int n = sc.nextInt();
        sc.nextLine(); // з'їсти кінець рядка після числа

        if (n <= 0) {
            System.out.println("Помилка: N має бути > 0. Завершення.");
            return;
        }

        System.out.print("Введіть символ-заповнювач (рівно 1 символ): ");
        String fillerStr = sc.nextLine();

        // Коректне переривання при не введенні або введенні кількох символів
        if (fillerStr == null || fillerStr.length() != 1) {
            System.out.println("Помилка: потрібно ввести рівно 1 символ. Завершення.");
            return;
        }

        char filler = fillerStr.charAt(0);

        // Генерація зубчатого масиву для "ромба"
        char[][] jagged = generateDiamondJaggedArray(n, filler);

        // Виведення в консоль (у вигляді N×N з пробілами поза ромбом)
        System.out.println("\nРезультат (ромб у квадратній матриці):");
        printAsSquareWithSpaces(jagged, n);

        // Запис у файл (також у форматі N×N з пробілами поза ромбом)
        try {
            writeToFileAsSquareWithSpaces(jagged, n, OUTPUT_FILE);
            System.out.println("\nЗаписано у файл: " + OUTPUT_FILE);
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    /**
     * Генерує зубчатий масив, що представляє ромб у квадраті N×N.
     * Зубчатість: у кожному рядку зберігаються лише символи ромба (без пробілів поза ним).
     *
     * <p>Логіка ромба (по рядках i = 0..N-1):</p>
     * <ul>
     *   <li>center = N/2</li>
     *   <li>dist = |i - center|</li>
     *   <li>width = N - 2*dist (ширина ромба в рядку)</li>
     * </ul>
     *
     * @param n      розмір квадратної матриці
     * @param filler символ заповнення ромба
     * @return зубчатий масив з рядками ширини ромба
     */
    public static char[][] generateDiamondJaggedArray(int n, char filler) {
        int center = n / 2;
        char[][] arr = new char[n][];

        for (int i = 0; i < n; i++) {
            int dist = Math.abs(i - center);
            int width = n - 2 * dist;

            // Для малих N (наприклад N=1) width буде 1, це ок.
            if (width < 1) {
                width = 1;
            }

            arr[i] = new char[width];
            for (int j = 0; j < width; j++) {
                arr[i][j] = filler;
            }
        }

        return arr;
    }

    /**
     * Виводить зубчатий масив як квадрат N×N: поза ромбом друкуються пробіли.
     *
     * @param jagged зубчатий масив ромба
     * @param n      розмір квадратної матриці
     */
    public static void printAsSquareWithSpaces(char[][] jagged, int n) {
        int center = n / 2;

        for (int i = 0; i < n; i++) {
            int dist = Math.abs(i - center);
            int leftSpaces = dist;              // зліва пробілів
            int width = jagged[i].length;       // ширина ромба

            // друк лівих пробілів
            for (int s = 0; s < leftSpaces; s++) {
                System.out.print(" ");
            }

            // друк ромба
            for (int j = 0; j < width; j++) {
                System.out.print(jagged[i][j]);
            }

            // добити правими пробілами до N (не обов'язково, але робимо "квадратність" видимою)
            int printed = leftSpaces + width;
            for (int s = printed; s < n; s++) {
                System.out.print(" ");
            }

            System.out.println();
        }
    }

    /**
     * Записує представлення ромба (у форматі квадрату N×N з пробілами поза ромбом) у файл.
     *
     * @param jagged     зубчатий масив ромба
     * @param n          розмір квадратної матриці
     * @param fileName   назва файлу
     * @throws IOException якщо виникли помилки вводу/виводу
     */
    public static void writeToFileAsSquareWithSpaces(char[][] jagged, int n, String fileName) throws IOException {
        int center = n / 2;

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < n; i++) {
                int dist = Math.abs(i - center);
                int leftSpaces = dist;
                int width = jagged[i].length;

                for (int s = 0; s < leftSpaces; s++) {
                    out.print(" ");
                }
                for (int j = 0; j < width; j++) {
                    out.print(jagged[i][j]);
                }
                int printed = leftSpaces + width;
                for (int s = printed; s < n; s++) {
                    out.print(" ");
                }
                out.println();
            }
        }
    }
}