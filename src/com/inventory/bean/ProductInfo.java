/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.bean;

/**
 *
 * @author nazmul hasan
 */
public class ProductInfo {
    private int id;
    private String name;
    private String code;
    
    private String length;
    private String width;
    private String height;
    private String weight;
    
    private double quantity;
    private double unitPrice;
    private double discount;
    private String purchaseOrderNo;
    
    private ProductCategoryInfo productCategoryInfo;
    private ProductTypeInfo productTypeInfo;
    private UOMInfo standardUOM;
    private UOMInfo saleUOM;
    private UOMInfo purchasingUOM;
    
    public ProductInfo()
    {
        productCategoryInfo = new ProductCategoryInfo();
        productTypeInfo = new ProductTypeInfo();
        standardUOM = new UOMInfo();
        saleUOM = new UOMInfo();
        purchasingUOM = new UOMInfo();
    }

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPurchaseOrderNo() {
        return purchaseOrderNo;
    }

    public void setPurchaseOrderNo(String purchaseOrderNo) {
        this.purchaseOrderNo = purchaseOrderNo;
    } 

    public ProductCategoryInfo getProductCategoryInfo() {
        return productCategoryInfo;
    }

    public void setProductCategoryInfo(ProductCategoryInfo productCategoryInfo) {
        this.productCategoryInfo = productCategoryInfo;
    }

    public ProductTypeInfo getProductTypeInfo() {
        return productTypeInfo;
    }

    public void setProductTypeInfo(ProductTypeInfo productTypeInfo) {
        this.productTypeInfo = productTypeInfo;
    }

    public UOMInfo getStandardUOM() {
        return standardUOM;
    }

    public void setStandardUOM(UOMInfo standardUOM) {
        this.standardUOM = standardUOM;
    }

    public UOMInfo getSaleUOM() {
        return saleUOM;
    }

    public void setSaleUOM(UOMInfo saleUOM) {
        this.saleUOM = saleUOM;
    }

    public UOMInfo getPurchasingUOM() {
        return purchasingUOM;
    }

    public void setPurchasingUOM(UOMInfo purchasingUOM) {
        this.purchasingUOM = purchasingUOM;
    }
    
}
