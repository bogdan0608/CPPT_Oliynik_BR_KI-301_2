package KI301.Oliynik.Lab6;

/**
 * Інтерфейс для об'єктів, які можна зберігати у сейфі та порівнювати за "цінністю".
 * Дає змогу класти в один сейф різні типи (наприклад, злитки та документи),
 * але порівнювати їх між собою коректно.
 */
public interface Valuable extends Comparable<Valuable> {

    /**
     * Повертає числову "цінність" об'єкта для порівняння.
     * @return цінність (чим більше — тим "дорожче")
     */
    double getValue();

    /**
     * Порівняння за цінністю (за замовчуванням).
     * @param other інший цінний об'єкт
     * @return результат compareTo
     */
    @Override
    default int compareTo(Valuable other) {
        return Double.compare(this.getValue(), other.getValue());
    }
}