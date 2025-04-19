package inventory.application;

import inventory.domain.Product;
import inventory.domain.IProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InventoryService {
    private final IProductRepository repository;

    public InventoryService(IProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String id, String name, int quantity, int criticalLevel) {
        var product = new Product(id, name, quantity, criticalLevel);
        repository.addProduct(product);
    }

    public void writeOffProduct(String id, int amount) {
        var product = getProductOrThrow(id);
        product.subtractQuantity(amount);
    }

    public void replenishProduct(String id, int amount) {
        var product = getProductOrThrow(id);
        product.addQuantity(amount);
    }

    public void adjustInventory(String id, int actualAmount) {
        var product = getProductOrThrow(id);
        int current = product.getQuantity();
        int diff = actualAmount - current;
        if (diff > 0) {
            product.addQuantity(diff);
        } else {
            product.subtractQuantity(-diff);
        }
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public List<Product> getCriticalProducts() {
        return repository.findAll().stream()
                .filter(Product::isCritical)
                .collect(Collectors.toList());
    }

    private Product getProductOrThrow(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт не найден"));
    }
}
