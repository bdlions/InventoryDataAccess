/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.inventory.datamodel;


/**
 *
 * @author alamgir
 */
public interface IProduct {
    public int getId();
    public String getName();
    public String getCode();
    public double getDiscount();
    public double getQuantity();
    public double getUnitPrice();
}