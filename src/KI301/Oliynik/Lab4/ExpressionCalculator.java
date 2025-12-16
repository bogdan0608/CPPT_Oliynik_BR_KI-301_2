package KI301.Oliynik.Lab4;

/**
 * Клас <code>ExpressionCalculator</code> реалізує обчислення
 * виразу y = ctg(x) / (sin(2x) + 4cos(x)).
 */
public class ExpressionCalculator {

    /**
     * Допустима похибка для перевірки ділення на нуль.
     */
    private static final double EPS = 1e-9;

    /**
     * Обчислити значення виразу
     * y = ctg(x) / (sin(2x) + 4cos(x)).
     *
     * @param x значення аргументу x (у радіанах)
     * @return значення y
     * @throws CalculationException якщо вираз не визначений
     */
    public double calculate(double x) throws CalculationException {

        double sinX = Math.sin(x);
        double cosX = Math.cos(x);

        // ctg(x) = cos(x) / sin(x), перевірка sin(x) ≠ 0
        if (Math.abs(sinX) < EPS) {
            throw new CalculationException("Помилка: ctg(x) не визначена при sin(x) = 0.");
        }

        double numerator = cosX / sinX; // ctg(x)
        double denominator = Math.sin(2 * x) + 4 * Math.cos(x);

        if (Math.abs(denominator) < EPS) {
            throw new CalculationException("Помилка: вираз не визначений (ділення на нуль у знаменнику).");
        }

        return numerator / denominator;
    }
}