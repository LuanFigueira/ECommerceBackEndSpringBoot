package LuanFigueiraOliveira.ECommerce.dto;

import LuanFigueiraOliveira.ECommerce.entities.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	private Long id;

	@Size(min = 3, max = 80, message = "Este campo precisa ter, no mínimo, 3 e, no máximo, 80 caracteres")
	@NotBlank(message = "Este campo precisa ser preenchido")
	private String name;

	@Size(min = 10, message = "Este campo precisa ter, no mínimo, 10 caracteres")
	@NotBlank(message = "Este campo precisa ser preenchido")
	private String description;

	@Positive(message = "Este campo só aceita valores positivos")
	private Double price;

	private String imgUrl;

	public ProductDTO() {}
	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.imgUrl = product.getImgUrl();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Double getPrice() {
		return price;
	}

	public String getImgUrl() {
		return imgUrl;
	}
}
