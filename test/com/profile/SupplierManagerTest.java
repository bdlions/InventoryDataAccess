package com.profile;

import com.inventory.bean.ProfileInfo;
import com.inventory.bean.SupplierInfo;
import com.inventory.db.manager.SupplierManager;
import com.inventory.response.ResultEvent;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author nazmul hasan
 */
public class SupplierManagerTest {
    
    public SupplierManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    
    //@Test
    public void createSupplierTest() 
    {
        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setFirstName("sa1");
        profileInfo.setLastName("sb1");
        profileInfo.setEmail("sc1");
        profileInfo.setFax("sd1");
        profileInfo.setPhone("se1");
        profileInfo.setWebsite("sf1");
        
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setRemarks("sr1");
        supplierInfo.setProfileInfo(profileInfo);
        
        SupplierManager supplierManager = new SupplierManager();
        ResultEvent resultEvent = supplierManager.createSupplier(supplierInfo);
        System.out.println(resultEvent.toString());
    }
    
    //@Test
    public void updateSupplierTest() 
    {
        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setId(53254355);
        profileInfo.setFirstName("sa1");
        profileInfo.setLastName("sb1");
        profileInfo.setEmail("sc1");
        profileInfo.setFax("sd1");
        profileInfo.setPhone("se1");
        profileInfo.setWebsite("sf1");
        
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setRemarks("sr1");
        supplierInfo.setProfileInfo(profileInfo);
        
        SupplierManager supplierManager = new SupplierManager();
        ResultEvent resultEvent = supplierManager.updateSupplier(supplierInfo);
        System.out.println(resultEvent.toString());
    }
    
    @Test
    public void getAllSuppliersTest() 
    {
        SupplierManager supplierManager = new SupplierManager();
        List<SupplierInfo> supplierList = supplierManager.getAllSuppliers();
        System.out.println("Total suppliers:"+supplierList.size());
    }
}
