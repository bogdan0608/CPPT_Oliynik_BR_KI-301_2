package KI301.Oliynik.Lab3;

import java.io.FileNotFoundException;

/**
 * Клас <code>MultiFunctionDevice</code> моделює багатофункціональний пристрій:
 * принтер + сканер + копір.
 *
 * Підклас розширює абстрактний клас {@link Printer} та реалізує
 * інтерфейси {@link Scannable} і {@link Copyable}.
 */
public class MultiFunctionDevice extends Printer implements Scannable, Copyable {

    /**
     * Кількість просканованих сторінок.
     */
    private int scannedPages;

    /**
     * Конструктор за замовчуванням.
     * Створює МФУ з типовими параметрами принтера.
     *
     * @throws FileNotFoundException якщо неможливо створити лог-файл
     */
    public MultiFunctionDevice() throws FileNotFoundException {
        super(); // викликаємо конструктор Printer()
        fout.println("Створено багатофункціональний пристрій (MFD) з параметрами за замовчуванням");
        fout.flush();
    }

    /**
     * Конструктор з параметрами.
     *
     * @param type           тип картриджа
     * @param inkPages       ресурс картриджа у сторінках
     * @param initialPaper   початкова кількість паперу
     * @param connectionType тип підключення (USB, WiFi тощо)
     * @throws FileNotFoundException якщо неможливо створити лог-файл
     */
    public MultiFunctionDevice(String type, int inkPages, int initialPaper, String connectionType)
            throws FileNotFoundException {
        super(type, inkPages, initialPaper, connectionType);
        fout.println("Створено багатофункціональний пристрій (MFD) з заданими параметрами");
        fout.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void scan(String documentName, int pages) {
        fout.println("Запит на сканування документа \"" + documentName + "\" (" + pages + " сторінок)");
        fout.flush();

        if (!poweredOn) {
            fout.println("Помилка сканування: пристрій вимкнений");
            fout.flush();
            return;
        }

        if (!connection.isConnected()) {
            fout.println("Помилка сканування: пристрій не підключений");
            fout.flush();
            return;
        }

        if (pages <= 0) {
            fout.println("Помилка сканування: некоректна кількість сторінок");
            fout.flush();
            return;
        }

        scannedPages += pages;
        fout.println("Документ \"" + documentName + "\" успішно проскановано");
        fout.flush();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScannedPages() {
        return scannedPages;
    }

    /**
     * {@inheritDoc}
     *
     * Копіювання моделюється як послідовне сканування та друк.
     */
    @Override
    public void copy(String documentName, int pages) {
        fout.println("Запит на копіювання документа \"" + documentName + "\" (" + pages + " сторінок)");
        fout.flush();

        if (!poweredOn) {
            fout.println("Помилка копіювання: пристрій вимкнений");
            fout.flush();
            return;
        }

        if (!connection.isConnected()) {
            fout.println("Помилка копіювання: пристрій не підключений");
            fout.flush();
            return;
        }

        if (!paperTray.hasPaper(pages)) {
            fout.println("Помилка копіювання: недостатньо паперу. Доступно: " + paperTray.getSheets());
            fout.flush();
            return;
        }

        if (!cartridge.hasInk(pages)) {
            fout.println("Помилка копіювання: недостатньо чорнила. Можна надрукувати ще: "
                    + cartridge.getRemainingPages() + " сторінок");
            fout.flush();
            return;
        }

        // спочатку "скануємо"
        scan(documentName, pages);

        // потім друкуємо копію, використовуючи механізм суперкласу
        printDocument("Копія: " + documentName, pages);
        fout.println("Копіювання завершено успішно");
        fout.flush();
    }

    /**
     * Отримати розширений статус MFD.
     *
     * @return рядок зі статусом, включаючи кількість просканованих сторінок
     */
    public String getExtendedStatus() {
        String base = getStatus();
        return base + ", Проскановано=" + scannedPages;
    }
}