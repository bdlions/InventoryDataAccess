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
public interface IProductDetails extends IProduct{
    public double getLength();
    public double getWidth();
    public double getHeight();
    public double getWieght();
}