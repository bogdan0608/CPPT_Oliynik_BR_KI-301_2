package KI301.Oliynik.Lab4;
import java.io.*;

/**
 * Клас ExpressionCalculatorIO розширює функціонал ExpressionCalculator,
 * додаючи методи читання та запису результатів у текстовому та двійковому форматах.
 */
public class ExpressionCalculatorIO {

    private ExpressionCalculator calculator;

    /**
     * Конструктор — створює об'єкт з калькулятором ЛР4.
     */
    public ExpressionCalculatorIO() {
        calculator = new ExpressionCalculator();
    }

    /**
     * Обчислити значення y = ctg(x)/(sin(2x) + 4cos(x)).
     *
     * @param x аргумент
     * @return значення функції
     * @throws CalculationException помилка обчислення
     */
    public double calculate(double x) throws CalculationException {
        return calculator.calculate(x);
    }

    /**
     * Запис результату у текстовий файл.
     */
    public void writeText(double x, double y, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println(x);
            writer.println(y);
        }
    }

    /**
     * Прочитати результат з текстового файла.
     *
     * @return масив double[]{x, y}
     */
    public double[] readText(String fileName) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            double x = Double.parseDouble(br.readLine());
            double y = Double.parseDouble(br.readLine());
            return new double[]{x, y};
        }
    }

    /**
     * Запис результату у двійковий файл.
     */
    public void writeBinary(double x, double y, String fileName) throws IOException {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName))) {
            out.writeDouble(x);
            out.writeDouble(y);
        }
    }

    /**
     * Прочитати результат з двійкового файла.
     *
     * @return масив double[]{x, y}
     */
    public double[] readBinary(String fileName) throws IOException {
        try (DataInputStream in = new DataInputStream(new FileInputStream(fileName))) {
            double x = in.readDouble();
            double y = in.readDouble();
            return new double[]{x, y};
        }
    }
}