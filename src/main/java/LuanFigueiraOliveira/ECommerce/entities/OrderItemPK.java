package LuanFigueiraOliveira.ECommerce.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class OrderItemPK {

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public OrderItemPK() {}
	public OrderItemPK(Order order, Product product) {
		this.order = order;
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		OrderItemPK orderItemPK = (OrderItemPK) o;
//		return Objects.equals(this.getOrder().getId(), orderItemPK.getOrder().getId()) && Objects.equals(this.getProduct().getId(), orderItemPK.getProduct().getId());
		return Objects.equals(order, orderItemPK.order) && Objects.equals(product, orderItemPK.product);
	}

	@Override
	public int hashCode() {
//		return Objects.hash(this.getOrder().getId(), this.getProduct().getId());
		return Objects.hash(order, product);
	}
}
