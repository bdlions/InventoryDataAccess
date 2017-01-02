package com.purchase;

import com.inventory.bean.PurchaseInfo;
import com.inventory.db.manager.PurchaseManager;
import com.inventory.response.ResultEvent;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alamgir
 */
public class PurchaseManagerTest {
    
    public PurchaseManagerTest() {
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

     //@Test
     public void getAllPurchaseOrdersTest() 
     {
         PurchaseManager purchaseManager = new PurchaseManager();
         List<PurchaseInfo> purchaseList = purchaseManager.getAllPurchaseOrders();
         System.out.println("Total purchase items:"+purchaseList.size());
     }
     
     //@Test
     public void searchPurchaseOrdersTest() 
     {
         PurchaseManager purchaseManager = new PurchaseManager();
         List<PurchaseInfo> purchaseList = purchaseManager.searchPurchaseOrders("order1");
         System.out.println("Total purchase items:"+purchaseList.size());
     }
     
     @Test
     public void getPurchaseOrderInfoTest() 
     {
         PurchaseManager purchaseManager = new PurchaseManager();
         ResultEvent resultEvent = purchaseManager.getPurchaseOrderInfo("order1");
         System.out.println("Total purchase items:");
     }
}
