package KI301.Oliynik.Lab3;

/**
 * Інтерфейс, що описує можливість копіювання документів.
 */
public interface Copyable {

    /**
     * Зробити копію документа.
     *
     * @param documentName назва документа
     * @param pages        кількість сторінок
     */
    void copy(String documentName, int pages);
}