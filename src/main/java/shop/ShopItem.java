package shop;

import java.math.BigDecimal;

public class ShopItem {
	private int id;
	private String name;
	private BigDecimal price;
	private String preview;
	private String previewLarge;
	private String description;
	private String keyword;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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
