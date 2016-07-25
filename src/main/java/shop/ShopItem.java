package shop;

import java.math.BigDecimal;

public class ShopItem {
	private int id;
	private String item;
	private BigDecimal price;
	private String preview;
	private String previewLarge;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getPreviewLarge() {
		return previewLarge;
	}
	public void setPreviewLarge(String previewLarge) {
		this.previewLarge = previewLarge;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
