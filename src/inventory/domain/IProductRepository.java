package inventory.domain;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    void addProduct(Product product);
    Optional<Product> findById(String id);
    List<Product> findAll();
}
