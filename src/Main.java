/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.inventory.datamodel.IProduct;
import com.inventory.datamodel.builder.ProductBuilder;

/**
 *
 * @author alamgir
 */
public class Main {
    public static void main(String[] args){
        
        IProduct a = new ProductBuilder().setId(10).setDiscount(100).build();
        IProduct b = new ProductBuilder().build("{\"product\":{\"id\":10,\"discount\":100.0}}");
        
    }
}
