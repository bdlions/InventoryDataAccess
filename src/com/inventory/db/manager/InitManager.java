package com.inventory.db.manager;

/**
 *
 * @author nazmul hasan
 */
public class InitManager {
    public InitManager()
    {
    
    }
    
    public boolean initConnection()
    {
        ProfileManager profileManager = new ProfileManager();
        profileManager.getAllAddressCategories();
        return true;
    }
}
