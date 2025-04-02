package LuanFigueiraOliveira.ECommerce.services;

import LuanFigueiraOliveira.ECommerce.dto.ProductDTO;
import LuanFigueiraOliveira.ECommerce.entities.Product;
import LuanFigueiraOliveira.ECommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> result = repository.findById(id);
		Product product = result.get();
		return new ProductDTO(product);
	}
}
