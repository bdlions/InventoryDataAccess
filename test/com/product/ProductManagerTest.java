package com.product;

import com.inventory.bean.ProductInfo;
import com.inventory.db.manager.ProductManager;
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
 * @author nazmul hasan
 */
public class ProductManagerTest {
    
    public ProductManagerTest() {
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
    public void createProductTest() 
    {
       ProductInfo productInfo1 = new ProductInfo();
       productInfo1.setName("name");
       productInfo1.setCode("code");
       productInfo1.setLength("length");
       productInfo1.setWidth("width");
       productInfo1.setHeight("height");
       productInfo1.setWeight("weight");
       productInfo1.setUnitPrice(1000);

       ProductManager productManager = new ProductManager();
       ResultEvent resultEvent = productManager.createProduct(productInfo1);
       System.out.println(resultEvent.getMessage());
    }
    
    //@Test
    public void updateProductTest() 
    {
       ProductInfo productInfo1 = new ProductInfo();
       productInfo1.setId(6);
       productInfo1.setName("name2");
       productInfo1.setCode("code2");
       productInfo1.setLength("length2");
       productInfo1.setWidth("width2");
       productInfo1.setHeight("height2");
       productInfo1.setWeight("weight2");
       productInfo1.setUnitPrice(2000);

       ProductManager productManager = new ProductManager();
       ResultEvent resultEvent = productManager.updateProduct(productInfo1);
       System.out.println(resultEvent.getMessage());
    }
    
    @Test
    public void searchProductTest() 
    {
       ProductManager productManager = new ProductManager();
       List<ProductInfo> productList = productManager.searchProduct("name2");
       System.out.println("Total products:"+productList.size());
    }
}
