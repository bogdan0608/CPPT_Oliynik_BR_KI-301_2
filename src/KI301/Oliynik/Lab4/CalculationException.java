package KI301.Oliynik.Lab4;

/**
 * Клас <code>CalculationException</code> описує помилки,
 * що виникають під час обчислення математичного виразу.
 */
public class CalculationException extends Exception {

    /**
     * Створює новий об'єкт виключення з вказаним повідомленням.
     *
     * @param message текст повідомлення про помилку
     */
    public CalculationException(String message) {
        super(message);
    }
}