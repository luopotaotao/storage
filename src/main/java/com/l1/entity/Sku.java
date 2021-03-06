package com.l1.entity;

public class Sku {
	private Integer id;
	private Integer itemId;
	private String itemName;
	private Double itemPrice;
	private Double itemRepo;
	private Integer colorId;
	private String colorName;

	private Integer sizeDtlId;
	private String sizeName;

	private String imgSuffix;
	private Integer amount;

	private String text;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

	public Double getItemRepo() {
		return itemRepo;
	}

	public void setItemRepo(Double itemRepo) {
		this.itemRepo = itemRepo;
	}

	public Integer getColorId() {
		return colorId;
	}

	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}

	public Integer getSizeDtlId() {
		return sizeDtlId;
	}

	public void setSizeDtlId(Integer sizeDtlId) {
		this.sizeDtlId = sizeDtlId;
	}

	public String getColorName() {
		return colorName;
	}

	public void setColorName(String colorName) {
		this.colorName = colorName;
	}

	public String getSizeName() {
		return sizeName;
	}

	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}

	public String getImgSuffix() {
		return imgSuffix;
	}

	public void setImgSuffix(String imgSuffix) {
		this.imgSuffix = imgSuffix;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
