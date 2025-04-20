package inventory.domain;

import java.time.LocalDate;

public class Product {
    private final String id;
    private final String name;
    private int quantity;
    private final int criticalLevel;
    private LocalDate expiryDate;

    public Product(String id, String name, int quantity, int criticalLevel, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.criticalLevel = criticalLevel;
        this.expiryDate = expiryDate;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public int getCriticalLevel() { return criticalLevel; }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    public void addQuantity(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Нельзя добавить отрицательное количество");
        quantity += amount;
    }

    public void subtractQuantity(int amount) {
        if (amount > quantity) throw new IllegalArgumentException("Недостаточно запасов");
        quantity -= amount;
    }

    public boolean isCritical() {
        return quantity <= criticalLevel;
    }

    @Override
    public String toString() {
        return "Продукт{id='" + id + "', название='" + name + "', количество=" + quantity +
                ", критический уровень=" + criticalLevel + ", срок годности=" + expiryDate + "}";
    }
}
