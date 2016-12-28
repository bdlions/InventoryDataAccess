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
public class CustomerInfo {
    private ProfileInfo profileInfo;
    public CustomerInfo()
    {
        profileInfo = new ProfileInfo();
    }

    public ProfileInfo getProfileInfo() {
        return profileInfo;
    }

    public void setProfileInfo(ProfileInfo userInfo) {
        this.profileInfo = userInfo;
    }
    
}
