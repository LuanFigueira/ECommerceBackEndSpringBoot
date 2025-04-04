package LuanFigueiraOliveira.ECommerce.services;

import LuanFigueiraOliveira.ECommerce.dto.ProductDTO;
import LuanFigueiraOliveira.ECommerce.entities.Product;
import LuanFigueiraOliveira.ECommerce.repositories.ProductRepository;
import LuanFigueiraOliveira.ECommerce.services.exceptions.DatabaseException;
import LuanFigueiraOliveira.ECommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		//Optional<Product> result = repository.findById(id);
		//Product product = result.get();
		Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
		return new ProductDTO(product);
	}

//	@Transactional(readOnly = true)
//	public List<ProductDTO> findAll() {
//		List<Product> products = repository.findAll();
//		return products.stream().map(product -> new ProductDTO(product)).toList();
//	}
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> products = repository.findAll(pageable);
		return products.map(product -> new ProductDTO(product));
	}

	@Transactional
	public ProductDTO insert (ProductDTO dto) {
		Product product = new Product();
		this.copyDtoToEntity(dto, product);
		product = repository.save(product);
		return new ProductDTO(product);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product product = repository.getReferenceById(id);
			this.copyDtoToEntity(dto, product);
			product = repository.save(product);
			return new ProductDTO(product);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if(!repository.existsById(id)){
			throw new ResourceNotFoundException("Recurso não encontrado");
		}
		try {
			repository.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial");
		}
	}

	private void copyDtoToEntity(ProductDTO dto, Product product) {
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setImgUrl(dto.getImgUrl());
	}
}