package inventory;

import inventory.application.InventoryService;
import inventory.infrastructure.InMemoryProductRepository;
import inventory.presentation.InventoryConsoleUI;

public class Main {
    public static void main(String[] args) {
        var productRepository = new InMemoryProductRepository();
        var service = new InventoryService(productRepository);

        service.addProduct("P001", "Булочки", 100, 20);
        service.addProduct("P002", "Котлеты", 50, 10);
        service.addProduct("P003", "Сыр", 80, 15);

        var ui = new InventoryConsoleUI(service);
        ui.start();
    }
}
