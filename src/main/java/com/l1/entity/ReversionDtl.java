package com.l1.entity;

import java.math.BigDecimal;

public class ReversionDtl {
    private Integer id;
    private Integer reversionId;

    private int stat;
    private String statName;

    private Integer skuId;
    private Integer itemId;
    private String itemName;
    private BigDecimal itemPrice;
    private Integer itemAmount;

    private Integer colorId;
    private String colorName;
    private Integer sizeId;
    private String sizeName;

    private Integer reversionStat;
    private String reversionStatName;

    private BigDecimal itemRent;
    private BigDecimal itemRepo;
    private BigDecimal itemCompensate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReversionId() {
        return reversionId;
    }

    public void setReversionId(Integer reversionId) {
        this.reversionId = reversionId;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
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

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public Integer getSizeId() {
        return sizeId;
    }

    public void setSizeId(Integer sizeId) {
        this.sizeId = sizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Integer getReversionStat() {
        return reversionStat;
    }

    public void setReversionStat(Integer reversionStat) {
        this.reversionStat = reversionStat;
    }

    public String getReversionStatName() {
        return reversionStatName;
    }

    public void setReversionStatName(String reversionStatName) {
        this.reversionStatName = reversionStatName;
    }

    public BigDecimal getItemRent() {
        return itemRent;
    }

    public void setItemRent(BigDecimal itemRent) {
        this.itemRent = itemRent;
    }

    public BigDecimal getItemRepo() {
        return itemRepo;
    }

    public void setItemRepo(BigDecimal itemRepo) {
        this.itemRepo = itemRepo;
    }

    public BigDecimal getItemCompensate() {
        return itemCompensate;
    }

    public void setItemCompensate(BigDecimal itemCompensate) {
        this.itemCompensate = itemCompensate;
    }
}
