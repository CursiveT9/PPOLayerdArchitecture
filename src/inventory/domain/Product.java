package inventory.domain;

public class Product {
    private final String id;
    private final String name;
    private int quantity;
    private final int criticalLevel;

    public Product(String id, String name, int quantity, int criticalLevel) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.criticalLevel = criticalLevel;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public int getCriticalLevel() { return criticalLevel; }

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
                ", критический уровень=" + criticalLevel + "}";
    }
}
