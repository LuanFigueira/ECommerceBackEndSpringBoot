package LuanFigueiraOliveira.ECommerce.controllers;

import LuanFigueiraOliveira.ECommerce.dto.ProductDTO;
import LuanFigueiraOliveira.ECommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

//	@GetMapping(value = "/{id}")
//	public ProductDTO findById(@PathVariable Long id) {
//		return service.findById(id);
//	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

//	@GetMapping
//	public List<ProductDTO> findAll() {
//		return service.findAll();
//	}
//	@GetMapping
//	public Page<ProductDTO> findAll(Pageable pageable) {
//		return service.findAll(pageable);
//	}
	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
		Page<ProductDTO> productDTOs = service.findAll(pageable);
		return ResponseEntity.ok(productDTOs);
	}

//	@PostMapping
//	public ProductDTO insert(@RequestBody ProductDTO dto) {
//		return service.insert(dto);
//	}
	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
