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
public class AddressInfo {
    private String address;
    private String city;
    private String state;
    private String zip;
    private AddressTypeInfo addressTypeInfo;
    private AddressCategoryInfo addressCategoryInfo;
    public AddressInfo()
    {
        addressTypeInfo = new AddressTypeInfo();
        addressCategoryInfo = new AddressCategoryInfo();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public AddressTypeInfo getAddressTypeInfo() {
        return addressTypeInfo;
    }

    public void setAddressTypeInfo(AddressTypeInfo addressTypeInfo) {
        this.addressTypeInfo = addressTypeInfo;
    }

    public AddressCategoryInfo getAddressCategoryInfo() {
        return addressCategoryInfo;
    }

    public void setAddressCategoryInfo(AddressCategoryInfo addressCategoryInfo) {
        this.addressCategoryInfo = addressCategoryInfo;
    }
    
}
