/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.datamodel.builder;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.inventory.datamodel.IProduct;


/**
 *
 * @author alamgir
 */
@JsonRootName(value = "product")
public class ProductBuilder extends ModelBuilder<IProduct> implements IProduct{
    private int id;
    private double discount;
    
    public ProductBuilder setId(int id){
        this.id = id;
        return this;
    }
    
    public ProductBuilder setDiscount(double discount){
        this.discount = discount;
        return this;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double getDiscount() {
        return discount;
    }

    @Override
    public String getName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getQuantity() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getUnitPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
