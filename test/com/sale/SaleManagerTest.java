package com.sale;

import com.inventory.bean.PurchaseInfo;
import com.inventory.bean.SaleInfo;
import com.inventory.db.manager.PurchaseManager;
import com.inventory.db.manager.SaleManager;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nazmul hasan
 */
public class SaleManagerTest {
    
    public SaleManagerTest() {
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
     public void getAllSaleOrdersTest() 
     {
         SaleManager saleManager = new SaleManager();
         List<SaleInfo> saleList = saleManager.getAllSaleOrders();
         System.out.println("Total sale items:"+saleList.size());
     }
     
     @Test
     public void searchSaleOrdersTest() 
     {
         SaleManager saleManager = new SaleManager();
         List<SaleInfo> saleList = saleManager.searchAllSaleOrders("order2");
         System.out.println("Total sale items:"+saleList.size());
     }
}
