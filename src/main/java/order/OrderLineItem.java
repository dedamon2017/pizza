package order;

import java.math.BigDecimal;

public class OrderLineItem {
	private int shopItemId;
	private int quantity;
	private String shopItemName;
	private BigDecimal shopItemPrice;

	public String getShopItemName() {
		return shopItemName;
	}

	public void setShopItemName(String shopItemName) {
		this.shopItemName = shopItemName;
	}

	public BigDecimal getShopItemPrice() {
		return shopItemPrice;
	}

	public void setShopItemPrice(BigDecimal shopItemPrice) {
		this.shopItemPrice = shopItemPrice;
	}

	public int getShopItemId() {
		return shopItemId;
	}

	public void setShopItemId(int shopItemId) {
		this.shopItemId = shopItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
