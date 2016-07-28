package order;

import java.math.BigDecimal;

public class OrderLineItem {
	private int shopItemId;
	private BigDecimal shopItemPrice;
	private int quantity;
	public int getShopItemId() {
		return shopItemId;
	}
	public void setShopItemId(int shopItemId) {
		this.shopItemId = shopItemId;
	}
	public BigDecimal getShopItemPrice() {
		return shopItemPrice;
	}
	public void setShopItemPrice(BigDecimal shopItemPrice) {
		this.shopItemPrice = shopItemPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
