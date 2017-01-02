package com.profile;

import com.inventory.bean.CustomerInfo;
import com.inventory.bean.ProfileInfo;
import com.inventory.db.manager.CustomerManager;
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
public class CustomerManagerTest {
    
    public CustomerManagerTest() {
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
    
    @Test
    public void createCustomerTest() 
    {
        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setFirstName("ca2");
        profileInfo.setLastName("cb2");
        //profileInfo.setEmail("cc2");
        //profileInfo.setFax("cd2");
        //profileInfo.setPhone("ce2");
        //profileInfo.setWebsite("cf2");
        
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setProfileInfo(profileInfo);
        
        CustomerManager customerManager = new CustomerManager();
        ResultEvent resultEvent = customerManager.createCustomer(customerInfo);
        System.out.println(resultEvent.toString());
    }
    
    //@Test
    public void updateCustomerTest() 
    {
        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setId(72767877);
        profileInfo.setFirstName("ca1");
        profileInfo.setLastName("cb1");
        profileInfo.setEmail("cc1");
        profileInfo.setFax("cd1");
        profileInfo.setPhone("ce1");
        profileInfo.setWebsite("cf1");
        
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setProfileInfo(profileInfo);
        
        CustomerManager customerManager = new CustomerManager();
        ResultEvent resultEvent = customerManager.updateCustomer(customerInfo);
        System.out.println(resultEvent.toString());
    }
    
    //@Test
    public void getAllCustomers() 
    {
        CustomerManager customerManager = new CustomerManager();
        List<CustomerInfo> customerList = customerManager.getAllCustomers();
        System.out.println("Total Customers:"+customerList.size());
    }
}
