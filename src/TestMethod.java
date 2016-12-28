
import com.inventory.bean.AddressCategoryInfo;
import com.inventory.bean.AddressInfo;
import com.inventory.bean.AddressTypeInfo;
import com.inventory.bean.CustomerInfo;
import com.inventory.bean.ProductCategoryInfo;
import com.inventory.bean.ProductInfo;
import com.inventory.bean.ProductTypeInfo;
import com.inventory.bean.PurchaseInfo;
import com.inventory.bean.SaleInfo;
import com.inventory.bean.SupplierInfo;
import com.inventory.bean.UOMInfo;
import com.inventory.bean.ProfileInfo;
import com.inventory.db.manager.CustomerManager;
import com.inventory.db.Database;
import com.inventory.db.manager.ProductManager;
import com.inventory.db.manager.ProfileManager;
import com.inventory.db.manager.PurchaseManager;
import com.inventory.db.manager.SaleManager;
import com.inventory.db.manager.StockManager;
import com.inventory.db.manager.SupplierManager;
import com.inventory.exceptions.DBSetupException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nazmul
 */
public class TestMethod {
    public static void main(String args[]) throws DBSetupException, SQLException
    {
        int customerUserId = 4791845;
        int supplierUserId = 9061303;
        // ------------------------ Initializing the database --------------------------//
//        Database.getInstance();
        // ------------------------ Adding products --------------------------//
//        ProductInfo productInfo1 = new ProductInfo();
//        productInfo1.setName("a1");
//        productInfo1.setCode("b1");
//        productInfo1.setLength("c1");
//        productInfo1.setWidth("d1");
//        productInfo1.setHeight("e1");
//        productInfo1.setWeight("f1");
//        productInfo1.setUnitPrice(100);
//        
//        
//        ProductManager productManager = new ProductManager();
//        productManager.createProduct(productInfo1);
//        
//        ProductInfo productInfo2 = new ProductInfo();
//        productInfo2.setName("a2");
//        productInfo2.setCode("b2");
//        productInfo2.setLength("c2");
//        productInfo2.setWidth("d2");
//        productInfo2.setHeight("e2");
//        productInfo2.setWeight("f2");
//        productInfo2.setUnitPrice(200);
//         productManager.createProduct(productInfo2);
        
        // ----- returning product category, type and uom list --------------------//
//        ProductManager productManager = new ProductManager();
//        List<ProductCategoryInfo> productCategoryList = productManager.getAllProductCategories();        
//        List<ProductTypeInfo> productTypeList = productManager.getAllProductTypes();        
//        List<UOMInfo> uomList = productManager.getAllUOMs();
        
        // ------------------------ returning productl ist --------------------------//
        ProductManager productManager = new ProductManager();
        List<ProductInfo> productList = productManager.getAllProducts();
        // ------------------------  Address Types and Categories ----------------------//
//        ProfileManager profileManager = new ProfileManager();
//        List<AddressTypeInfo> addressTypeList = profileManager.getAllAddressTypes();
//        List<AddressCategoryInfo> addressCategoryList = profileManager.getAllAddressCategories();
          // ------------------------ Adding a new Supplier --------------------------//
//         UserInfo userInfo = new UserInfo();
//          userInfo.setFirstName("fn2");
//          userInfo.setLastName("ln2");
//          userInfo.setEmail("email2");
//          userInfo.setPhone("phone2");
//          userInfo.setFax("fax2");
//          userInfo.setWebsite("website2");
//          
//          AddressInfo addressInfo = new AddressInfo();
//          addressInfo.getAddressTypeInfo().setId(1);
//          addressInfo.getAddressCategoryInfo().setId(1);
//          addressInfo.setAddress("niketon");
//          addressInfo.setCity("dhaka");
//          addressInfo.setState("dhaka");
//          addressInfo.setZip("1207");
//          
//          List<AddressInfo> addresses = new ArrayList<>();
//          addresses.add(addressInfo);
//          userInfo.setAddresses(addresses);
//          
//          SupplierInfo supplierInfo = new SupplierInfo();
//          supplierInfo.setUserInfo(userInfo);
//          supplierInfo.setRemarks("remarks2");
//          
//          SupplierManager supplierManager = new SupplierManager();
//          supplierManager.createSupplier(supplierInfo);
          // ------------------------ Adding a new Purchase Order --------------------------//
//          ProductInfo productInfo1 = new ProductInfo();
//          productInfo1.setId(1);
//          productInfo1.setUnitPrice(100);
//          productInfo1.setQuantity(500);
//          productInfo1.setDiscount(0);
//          
//          ProductInfo productInfo2 = new ProductInfo();
//          productInfo2.setId(2);
//          productInfo2.setUnitPrice(100);
//          productInfo2.setQuantity(600);
//          productInfo2.setDiscount(0);
//          
//          List<ProductInfo> productList = new ArrayList<>();
//          productList.add(productInfo1);
//          productList.add(productInfo2);
//          
//          PurchaseInfo purchaseInfo = new PurchaseInfo();
//          purchaseInfo.setProductList(productList);
//          purchaseInfo.setSupplierUserId(supplierUserId);
//          purchaseInfo.setOrderNo("order1");
//          purchaseInfo.setStatusId(1);
//          purchaseInfo.setRemarks("remarks1");
//          //purchaseInfo.setOrderDate();
//          purchaseInfo.setRequestShippedDate(456);
//          
//          PurchaseManager purchaseManager = new PurchaseManager();
//          purchaseManager.addPurchaseOrder(purchaseInfo);
        // ------------------------ Adding a new Customer --------------------------//
//          UserInfo userInfo = new UserInfo();
//          userInfo.setFirstName("fn3");
//          userInfo.setLastName("ln5");
//          userInfo.setEmail("email5");
//          userInfo.setPhone("phone5");
//          userInfo.setFax("fax5");
//          userInfo.setWebsite("website5");
//          
//          AddressInfo addressInfo = new AddressInfo();
//          addressInfo.getAddressTypeInfo().setId(1);
//          addressInfo.getAddressCategoryInfo().setId(1);
//          addressInfo.setAddress("niketon");
//          addressInfo.setCity("dhaka");
//          addressInfo.setState("dhaka");
//          addressInfo.setZip("1507");
//          
//          List<AddressInfo> addresses = new ArrayList<>();
//          addresses.add(addressInfo);
//          userInfo.setAddresses(addresses);
//          
//          CustomerInfo customerInfo = new CustomerInfo();
//          customerInfo.setUserInfo(userInfo);
//          
//          CustomerManager customerManager = new CustomerManager();
//          customerManager.createCustomer(customerInfo);
        // ------------------------ Adding a new Sale order --------------------------//
//          ProductInfo productInfo1 = new ProductInfo();
//          productInfo1.setId(1);
//          productInfo1.setUnitPrice(100);
//          productInfo1.setQuantity(300);
//          productInfo1.setDiscount(0);
//          productInfo1.setPurchaseOrderNo("order1");
//          
//          ProductInfo productInfo2 = new ProductInfo();
//          productInfo2.setId(2);
//          productInfo2.setUnitPrice(100);
//          productInfo2.setQuantity(400);
//          productInfo2.setDiscount(0);
//          productInfo2.setPurchaseOrderNo("order1");
//          
//          List<ProductInfo> productList = new ArrayList<>();
//          productList.add(productInfo1);
//          productList.add(productInfo2);
//          
//          SaleInfo saleInfo = new SaleInfo();
//          saleInfo.setProductList(productList);
//          saleInfo.setCustomerUserId(customerUserId);
//          saleInfo.setOrderNo("order2");
//          saleInfo.setStatusId(1);
//          saleInfo.setRemarks("remarks2");
//          saleInfo.setSaleDate(123);
//          
//          SaleManager saleManager = new SaleManager();
//          saleManager.addSaleOrder(saleInfo);
        
        // ------------------------ Returning supplier list --------------------------//
//        SupplierManager supplierManager = new SupplierManager();
//        List<SupplierInfo> supplierList = supplierManager.getAllSuppliers();
        // ------------------------ Returning customer list --------------------------//
//        CustomerManager customerManager = new CustomerManager();
//        List<CustomerInfo> customerList = customerManager.getAllCustomers();
        // ------------------------ Returning Purchase list --------------------------//
//        PurchaseManager purchaseManager = new PurchaseManager();
//        List<PurchaseInfo> purchaseList = purchaseManager.getAllPurchaseOrders();
        // ------------------------ Returning Sale list --------------------------//
//        SaleManager saleManager = new SaleManager();
//        List<SaleInfo> saleList = saleManager.getAllSaleOrders();
        // ------------------------ Returning Current stock --------------------------//
//        List<ProductInfo> productList = new ArrayList<>();
//        StockManager stockManager = new StockManager();
//        productList = stockManager.getCurrentStocks();
        System.out.println("Test completed.");
    }
}
