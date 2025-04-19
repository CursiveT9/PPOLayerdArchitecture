package inventory.infrastructure;

import inventory.domain.Product;
import inventory.domain.IProductRepository;

import java.util.*;

public class InMemoryProductRepository implements IProductRepository {
    private final Map<String, Product> storage = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        storage.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(storage.values());
    }
}
