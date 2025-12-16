package KI301.Oliynik.lab2;

import static java.lang.System.out;

/**
 * Клас-драйвер для демонстрації роботи класу {@link Printer}.
 * Виконує тестування основних можливостей принтера.
 */
public class PrinterDriver {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     * @throws Exception можливі винятки під час створення об’єкта принтера
     */
    public static void main(String[] args) throws Exception {

        Printer printer = new Printer(); // принтер з параметрами за замовчуванням

        printer.powerOn();
        printer.connect();

        printer.loadPaper(20);
        printer.printTestPage();
        printer.printDocument("Lab2_Oliinyk.docx", 5);
        printer.printDocument("Report.pdf", 30); // спроба роздрукувати більше ніж є паперу

        out.println("Статус: " + printer.getStatus());
        out.println("Надруковано сторінок: " + printer.getPrintedPages());
        out.println("Готовність: " + printer.isReady());

        printer.powerOff();
        printer.dispose(); // коректне завершення роботи з лог-файлом
    }
}