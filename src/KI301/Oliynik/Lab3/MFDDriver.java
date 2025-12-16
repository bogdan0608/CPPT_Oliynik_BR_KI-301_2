package KI301.Oliynik.Lab3;

import static java.lang.System.out;

/**
 * Клас-драйвер для демонстрації роботи багатофункціонального пристрою
 * {@link MultiFunctionDevice}.
 */
public class MFDDriver {

    /**
     * Точка входу в програму.
     *
     * @param args аргументи командного рядка
     * @throws Exception можливі винятки під час створення об’єкта пристрою
     */
    public static void main(String[] args) throws Exception {

        MultiFunctionDevice mfd = new MultiFunctionDevice();

        mfd.powerOn();
        mfd.connect();

        mfd.loadPaper(30);

        // звичайний друк
        mfd.printTestPage();
        mfd.printDocument("Lab3_Oliinyk.docx", 5);

        // сканування
        mfd.scan("PassportScan", 2);

        // копіювання
        mfd.copy("Contract", 4);

        out.println("Статус MFD: " + mfd.getExtendedStatus());
        out.println("Надруковано сторінок: " + mfd.getPrintedPages());
        out.println("Проскановано сторінок: " + mfd.getScannedPages());
        out.println("Готовність: " + mfd.isReady());

        mfd.powerOff();
        mfd.dispose();
    }
}