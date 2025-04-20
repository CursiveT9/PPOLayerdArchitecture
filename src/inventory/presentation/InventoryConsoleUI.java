package inventory.presentation;

import inventory.application.InventoryService;
import inventory.domain.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class InventoryConsoleUI {
    private final InventoryService service;
    private final Scanner scanner = new Scanner(System.in);

    public InventoryConsoleUI(InventoryService service) {
        this.service = service;
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // очищаем
            handleChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n===== Инвентаризация ресторана =====");
        System.out.println("1. Добавить продукт");
        System.out.println("2. Списать продукт");
        System.out.println("3. Пополнить продукт");
        System.out.println("4. Провести инвентаризацию (коррекция)");
        System.out.println("5. Показать все продукты");
        System.out.println("6. Показать продукты с критическим уровнем");
        System.out.println("7. Показать просроченные продукты");
        System.out.println("8. Списать просроченные продукты");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addProduct();
            case 2 -> writeOffProduct();
            case 3 -> replenishProduct();
            case 4 -> adjustInventory();
            case 5 -> showAllProducts();
            case 6 -> showCriticalProducts();
            case 7 -> showExpiredProducts();
            case 8 -> writeOffExpiredProducts();
            case 0 -> System.out.println("Выход...");
            default -> System.out.println("Неверный выбор.");
        }
    }

    private void adjustInventory() {
        System.out.print("ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Фактическое количество: ");
        int actual = scanner.nextInt();

        try {
            service.adjustInventory(id, actual);
            System.out.println("Инвентаризация выполнена.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void writeOffProduct() {
        System.out.print("ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Сколько списать: ");
        int amount = scanner.nextInt();

        try {
            service.writeOffProduct(id, amount);
            System.out.println("Продукт списан.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void replenishProduct() {
        System.out.print("ID продукта: ");
        String id = scanner.nextLine();
        System.out.print("Сколько добавить: ");
        int amount = scanner.nextInt();

        try {
            service.replenishProduct(id, amount);
            System.out.println("Продукт пополнен.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void addProduct() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Название: ");
        String name = scanner.nextLine();
        System.out.print("Количество: ");
        int quantity = scanner.nextInt();
        System.out.print("Критический уровень: ");
        int level = scanner.nextInt();
        scanner.nextLine(); // очистка буфера

        System.out.print("Срок годности (ГГГГ-ММ-ДД): ");
        String expiryDateStr = scanner.nextLine();
        LocalDate expiryDate = LocalDate.parse(expiryDateStr);

        try {
            service.addProduct(id, name, quantity, level, expiryDate);
            System.out.println("Продукт добавлен.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void showExpiredProducts() {
        List<Product> expired = service.getExpiredProducts();
        if (expired.isEmpty()) {
            System.out.println("Нет просроченных продуктов.");
        } else {
            System.out.println("Просроченные продукты:");
            expired.forEach(System.out::println);
        }
    }

    private void writeOffExpiredProducts() {
        try {
            service.writeOffExpiredProducts();
            System.out.println("Просроченные продукты списаны.");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private void showAllProducts() {
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Нет продуктов в инвентаре.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private void showCriticalProducts() {
        List<Product> critical = service.getCriticalProducts();
        if (critical.isEmpty()) {
            System.out.println("Нет продуктов с критическим уровнем.");
        } else {
            System.out.println("Продукты с критическим уровнем:");
            critical.forEach(System.out::println);
        }
    }
}
