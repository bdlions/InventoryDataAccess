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
       productInfo1.setName("pn1");
       productInfo1.setCode("pc1");
       productInfo1.setLength("pl1");
       productInfo1.setWidth("pw1");
       productInfo1.setHeight("ph1");
       productInfo1.setWeight("pwt1");
       productInfo1.setUnitPrice(1000);

       ProductManager productManager = new ProductManager();
       ResultEvent resultEvent = productManager.createProduct(productInfo1);
       System.out.println(resultEvent.toString());
    }
    
    //@Test
    public void updateProductTest() 
    {
       ProductInfo productInfo1 = new ProductInfo();
       productInfo1.setId(8);
       productInfo1.setName("pn2");
       productInfo1.setCode("pc2");
       productInfo1.setLength("pl2");
       productInfo1.setWidth("pw2");
       productInfo1.setHeight("ph2");
       productInfo1.setWeight("pwt2");
       productInfo1.setUnitPrice(2000);

       ProductManager productManager = new ProductManager();
       ResultEvent resultEvent = productManager.updateProduct(productInfo1);
       System.out.println(resultEvent.toString());
    }
    
    @Test
    public void getAllProductsTest() 
    {
       ProductManager productManager = new ProductManager();
       List<ProductInfo> productList = productManager.getAllProducts();
       System.out.println("Total products:"+productList.size());
    }
    
    //@Test
    public void searchProductTest() 
    {
       ProductManager productManager = new ProductManager();
       List<ProductInfo> productList = productManager.searchProduct("name2");
       System.out.println("Total products:"+productList.size());
    }
}
