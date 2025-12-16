package KI301.Oliynik.Lab6;

import java.util.Scanner;

/**
 * Інтерактивний драйвер для контейнера Safe.
 * Користувач сам керує додаванням і вилученням елементів.
 */
public class Lab6Driver {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Safe<Valuable> safe = new Safe<>(5);

        System.out.println("=== LAB 6 / Варіант 19: Сейф ===");
        System.out.println("Сейф створено. Початковий стан — порожній.\n");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Ваш вибір: ");

            try {
                switch (choice) {
                    case 1 -> addGoldBar(safe);
                    case 2 -> addDocument(safe);
                    case 3 -> removeByIndex(safe);
                    case 4 -> removeLast(safe);
                    case 5 -> showSafe(safe);
                    case 6 -> showMax(safe);
                    case 0 -> {
                        System.out.println("Завершення роботи програми.");
                        running = false;
                    }
                    default -> System.out.println("Невірний пункт меню.\n");
                }
            } catch (Exception e) {
                System.out.println("Помилка: " + e.getMessage() + "\n");
            }
        }
    }

    // ===== МЕНЮ =====
    private static void printMenu() {
        System.out.println("""
                -------- МЕНЮ --------
                1 - Додати золотий злиток
                2 - Додати конфіденційний документ
                3 - Вийняти елемент за індексом
                4 - Вийняти останній елемент
                5 - Показати вміст сейфа
                6 - Показати найдорожчий елемент
                0 - Вихід
                ----------------------
                """);
    }

    // ===== ОПЕРАЦІЇ =====

    private static void addGoldBar(Safe<Valuable> safe) {
        System.out.println("Додавання золотого злитка:");

        System.out.print("Серійний номер: ");
        String serial = sc.nextLine();

        double weight = readDouble("Вага (г): ");
        double price = readDouble("Ціна за 1 г: ");

        GoldBar bar = new GoldBar(serial, weight, price);
        safe.put(bar);

        System.out.println("Успішно додано:");
        System.out.println("  " + bar + "\n");
    }

    private static void addDocument(Safe<Valuable> safe) {
        System.out.println("Додавання конфіденційного документа:");

        System.out.print("Назва: ");
        String title = sc.nextLine();

        int level = readInt("Рівень секретності (1–10): ");
        double value = readDouble("Базова вартість: ");

        ConfidentialDocument doc = new ConfidentialDocument(title, level, value);
        safe.put(doc);

        System.out.println("Успішно додано:");
        System.out.println("  " + doc + "\n");
    }

    private static void removeByIndex(Safe<Valuable> safe) {
        if (safe.isEmpty()) {
            System.out.println("Сейф порожній.\n");
            return;
        }

        showSafe(safe);
        int index = readInt("Введіть індекс для вилучення: ");

        Valuable removed = safe.takeAt(index);
        System.out.println("Вилучено:");
        System.out.println("  " + removed + "\n");
    }

    private static void removeLast(Safe<Valuable> safe) {
        if (safe.isEmpty()) {
            System.out.println("Сейф порожній.\n");
            return;
        }

        Valuable removed = safe.takeLast();
        System.out.println("Вилучено останній елемент:");
        System.out.println("  " + removed + "\n");
    }

    private static void showSafe(Safe<Valuable> safe) {
        System.out.println("Поточний стан сейфа:");
        System.out.print(safe);
        System.out.println();
    }

    private static void showMax(Safe<Valuable> safe) {
        if (safe.isEmpty()) {
            System.out.println("Сейф порожній.\n");
            return;
        }

        Valuable max = safe.findMax();
        System.out.println("Найдорожчий елемент у сейфі:");
        System.out.println("  " + max + "\n");
    }

    // ===== ДОПОМІЖНІ МЕТОДИ ВВОДУ =====

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Введіть коректне ціле число.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(sc.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Введіть коректне число.");
            }
        }
    }
}