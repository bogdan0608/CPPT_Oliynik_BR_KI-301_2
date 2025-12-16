package KI301.Oliynik.Lab3;

/**
 * Інтерфейс, що описує можливість сканування документів.
 */
public interface Scannable {

    /**
     * Просканувати документ.
     *
     * @param documentName назва документа
     * @param pages        кількість сторінок
     */
    void scan(String documentName, int pages);

    /**
     * Отримати кількість просканованих сторінок.
     *
     * @return кількість сторінок
     */
    int getScannedPages();
}