package KI301.Oliynik.Lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Клас-контейнер "Сейф" для зберігання цінних об'єктів.
 * <p>
 * Варіант 19 (непарний): реалізовано пошук максимального елемента.
 * </p>
 *
 * @param <T> тип елементів, які можна класти в сейф (мають бути Valuable)
 */
public class Safe<T extends Valuable> {

    private final List<T> items;
    private final int capacity;

    /**
     * Створює сейф з обмеженою місткістю.
     *
     * @param capacity максимальна кількість елементів у сейфі (capacity > 0)
     */
    public Safe(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be > 0");
        }
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    /**
     * Розміщує елемент у сейфі (операція "покласти").
     *
     * @param item елемент для додавання
     * @throws IllegalStateException якщо сейф переповнений
     */
    public void put(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (items.size() >= capacity) {
            throw new IllegalStateException("Safe is full");
        }
        items.add(item);
    }

    /**
     * Виймає елемент з сейфа за індексом (операція "вийняти").
     *
     * @param index індекс елемента
     * @return вилучений елемент
     * @throws IndexOutOfBoundsException якщо індекс некоректний
     */
    public T takeAt(int index) {
        return items.remove(index);
    }

    /**
     * Виймає останній доданий елемент (LIFO-логіка як приклад швидкого доступу).
     *
     * @return вилучений елемент
     * @throws NoSuchElementException якщо сейф порожній
     */
    public T takeLast() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("Safe is empty");
        }
        return items.remove(items.size() - 1);
    }

    /**
     * Повертає (без вилучення) максимальний елемент за цінністю.
     * Для варіанту 19 (непарний) — пошук МАКСИМАЛЬНОГО.
     *
     * @return максимальний елемент
     * @throws NoSuchElementException якщо сейф порожній
     */
    public T findMax() {
        if (items.isEmpty()) {
            throw new NoSuchElementException("Safe is empty");
        }
        T max = items.get(0);
        for (T item : items) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * Перевіряє, чи сейф порожній.
     *
     * @return true якщо немає елементів
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Поточна кількість елементів у сейфі.
     *
     * @return size
     */
    public int size() {
        return items.size();
    }

    /**
     * Максимальна місткість сейфа.
     *
     * @return capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Повертає строкове представлення вмісту сейфа.
     *
     * @return опис сейфа
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Сейф: ").append(size()).append("/").append(capacity).append("\n");

        if (items.isEmpty()) {
            sb.append("  (порожньо)\n");
            return sb.toString();
        }

        for (int i = 0; i < items.size(); i++) {
            sb.append(String.format("  %d) %s%n", i, items.get(i)));
        }
        return sb.toString();
    }
}