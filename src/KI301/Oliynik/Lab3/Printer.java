package KI301.Oliynik.Lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Абстрактний клас <code>Printer</code> моделює роботу простого принтера.
 * Слугує суперкласом для більш складних друкувальних пристроїв.
 */
public abstract class Printer extends {
    protected Cartridge cartridge;
    protected PaperTray paperTray;
    protected Connection connection;

    protected boolean poweredOn;
    protected int printedPages;

    protected PrintWriter fout; // лог-файл

    /**
     * Конструктор за замовчуванням.
     * Створює принтер з початковими стандартними параметрами.
     *
     * @throws FileNotFoundException якщо неможливо створити лог-файл
     */
    public Printer() throws FileNotFoundException {

        cartridge = new KI301.Oliynik.Lab3.Cartridge("Black", 100); // чорнила на 100 сторінок
        paperTray = new KI301.Oliynik.Lab3.PaperTray(10);           // 10 аркушів у лотку
        connection = new KI301.Oliynik.Lab3.Connection("USB");
        poweredOn = false;
        printedPages = 0;

        fout = new PrintWriter(new File("PrinterLog.txt"));
        fout.println("Створено принтер із параметрами за замовчуванням");
        fout.flush();
    }

    /**
     * Конструктор з параметрами.
     *
     * @param type тип картриджа
     * @param inkPages кількість сторінок, які можна надрукувати з цим картриджем
     * @param initialPaper початкова кількість паперу
     * @param connectionType тип підключення принтера
     * @throws FileNotFoundException якщо неможливо створити лог-файл
     */
    public Printer(String type, int inkPages, int initialPaper, String connectionType)
            throws FileNotFoundException {

        cartridge = new KI301.Oliynik.Lab3.Cartridge(type, inkPages);
        paperTray = new KI301.Oliynik.Lab3.PaperTray(initialPaper);
        connection = new KI301.Oliynik.Lab3.Connection(connectionType);
        poweredOn = false;
        printedPages = 0;

        fout = new PrintWriter(new File("PrinterLog.txt"));
        fout.println("Створено принтер із заданими параметрами");
        fout.flush();
    }

    /**
     * Увімкнути принтер.
     */
    public void powerOn() {
        poweredOn = true;
        fout.println("Принтер увімкнено");
        fout.flush();
    }

    /**
     * Вимкнути принтер.
     */
    public void powerOff() {
        poweredOn = false;
        fout.println("Принтер вимкнено");
        fout.flush();
    }

    /**
     * Підключити принтер до комп’ютера / мережі.
     */
    public void connect() {
        connection.connect();
        fout.println("Принтер підключено (" + connection.getType() + ")");
        fout.flush();
    }

    /**
     * Відключити принтер.
     */
    public void disconnect() {
        connection.disconnect();
        fout.println("Принтер відключено");
        fout.flush();
    }

    /**
     * Додати папір до лотка.
     *
     * @param sheets кількість аркушів для додавання
     */
    public void loadPaper(int sheets) {
        paperTray.addPaper(sheets);
        fout.println("Додано папір: " + sheets + " аркушів. Тепер у лотку: " + paperTray.getSheets());
        fout.flush();
    }

    /**
     * Надрукувати документ з указаною кількістю сторінок.
     *
     * @param name назва документа
     * @param pages кількість сторінок
     */
    public void printDocument(String name, int pages) {
        fout.println("Запит на друк документа \"" + name + "\" (" + pages + " сторінок)");
        fout.flush();

        if (!poweredOn) {
            fout.println("Помилка: принтер вимкнений");
            fout.flush();
            return;
        }
        if (!connection.isConnected()) {
            fout.println("Помилка: принтер не підключений");
            fout.flush();
            return;
        }
        if (!paperTray.hasPaper(pages)) {
            fout.println("Помилка: недостатньо паперу. Доступно: " + paperTray.getSheets());
            fout.flush();
            return;
        }
        if (!cartridge.hasInk(pages)) {
            fout.println("Помилка: недостатньо чорнила. Можна надрукувати ще: "
                    + cartridge.getRemainingPages() + " сторінок");
            fout.flush();
            return;
        }

        paperTray.usePaper(pages);
        cartridge.useInk(pages);
        printedPages += pages;

        fout.println("Документ \"" + name + "\" успішно надруковано");
        fout.flush();
    }

    /**
     * Друкує одну тестову сторінку.
     */
    public void printTestPage() {
        fout.println("Друк тестової сторінки");
        fout.flush();
        printDocument("Test page", 1);
    }

    /**
     * Замінити картридж на новий.
     *
     * @param newCartridge новий картридж
     */
    public void replaceCartridge(KI301.Oliynik.Lab3.Cartridge newCartridge) {
        this.cartridge = newCartridge;
        fout.println("Картридж замінено. Тип: " + newCartridge.getType());
        fout.flush();
    }

    /**
     * Отримати текстовий опис поточного стану принтера.
     *
     * @return рядок зі статусом принтера
     */
    public String getStatus() {
        String status = "Увімкнений=" + poweredOn
                + ", Підключений=" + connection.isConnected()
                + ", Папір=" + paperTray.getSheets()
                + ", Чорнило(сторінок)=" + cartridge.getRemainingPages()
                + ", Надруковано=" + printedPages;

        fout.println("Запитано статус: " + status);
        fout.flush();
        return status;
    }

    /**
     * Перевірити, чи принтер готовий до друку.
     *
     * @return true, якщо всі умови для друку виконані
     */
    public boolean isReady() {
        boolean ready = poweredOn
                && connection.isConnected()
                && paperTray.getSheets() > 0
                && cartridge.getRemainingPages() > 0;

        fout.println("Перевірка готовності: " + ready);
        fout.flush();
        return ready;
    }

    /**
     * Отримати кількість надрукованих сторінок.
     *
     * @return кількість сторінок
     */
    public int getPrintedPages() {
        return printedPages;
    }

    /**
     * Коректне завершення роботи принтера та закриття лог-файлу.
     * Використовується замість застарілого finalize().
     */
    public void dispose() {
        fout.println("Принтер завершив роботу");
        fout.flush();
        fout.close();
    }
}


/**
 * Клас <code>Cartridge</code> моделює картридж принтера.
 */
class Cartridge {

    private String type;
    private int remainingPages;

    /**
     * @param type тип картриджа
     * @param remainingPages кількість сторінок, які ще можна надрукувати
     */
    public Cartridge(String type, int remainingPages) {
        this.type = type;
        this.remainingPages = Math.max(0, remainingPages);
    }

    /**
     * Використати чорнило для друку певної кількості сторінок.
     */
    public void useInk(int pages) {
        if (pages <= 0) return;
        remainingPages = Math.max(0, remainingPages - pages);
    }

    /**
     * Перевірити наявність чорнила.
     */
    public boolean hasInk(int pages) {
        return remainingPages >= pages;
    }

    public String getType() {
        return type;
    }

    public int getRemainingPages() {
        return remainingPages;
    }
}

/**
 * Клас <code>PaperTray</code> моделює лоток для паперу.
 */
class PaperTray {

    private int sheets;

    /**
     * @param sheets початкова кількість аркушів
     */
    public PaperTray(int sheets) {
        this.sheets = Math.max(0, sheets);
    }

    /**
     * Додати певну кількість паперу.
     */
    public void addPaper(int sheets) {
        if (sheets <= 0) return;
        this.sheets += sheets;
    }

    /**
     * Використати певну кількість паперу.
     */
    public void usePaper(int sheets) {
        if (sheets <= 0) return;
        this.sheets = Math.max(0, this.sheets - sheets);
    }

    /**
     * Перевірити, чи достатньо паперу.
     */
    public boolean hasPaper(int needed) {
        return sheets >= needed;
    }

    public int getSheets() {
        return sheets;
    }
}

/**
 * Клас <code>Connection</code> моделює модуль підключення принтера.
 */
class Connection {

    private String type;
    private boolean connected;

    /**
     * @param type тип підключення (USB, WiFi тощо)
     */
    public Connection(String type) {
        this.type = type;
        this.connected = false;
    }

    /**
     * Підключити принтер.
     */
    public void connect() {
        connected = true;
    }

    /**
     * Відключити принтер.
     */
    public void disconnect() {
        connected = false;
    }

    public boolean isConnected() {
        return connected;
    }

    public String getType() {
        return type;
    }
}