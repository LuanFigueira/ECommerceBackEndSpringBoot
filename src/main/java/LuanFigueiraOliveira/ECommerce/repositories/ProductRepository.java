package LuanFigueiraOliveira.ECommerce.repositories;

import LuanFigueiraOliveira.ECommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
