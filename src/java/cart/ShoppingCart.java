/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entity.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GOGO
 */
public class ShoppingCart {

    List<ShoppingCartItem> items;
    int numberOfItems;
    double total;

    public ShoppingCart() {
         items = new ArrayList<ShoppingCartItem>();
         numberOfItems = 0;
         total = 0;
    }

    public synchronized double getTotal() {
        return total;
    }

    public synchronized List<ShoppingCartItem> getItems() {
        return items;
    }
    
    

    public synchronized void addItem(Product product) {

        boolean newItem=true;

        for (ShoppingCartItem scItems : items) {

            if (scItems.getProduct().getProductId()== product.getProductId()) {
                newItem=false;
                scItems.incrementQuantity();
            }
        }
        if(newItem){
            ShoppingCartItem scItems = new ShoppingCartItem(product);
            items.add(scItems);
        }

    }
    
    public synchronized void update(Product product ,String quantity){
        short qty = -1;// metod her çağırılmada eksi değerle başlar böylece -
        //sıfırdan küçük olduğu durumlarda aşağıdaki if blokları çalışmaz.
        
        qty = Short.parseShort(quantity);
        //cast ettik
        
        if(qty >=0){
            ShoppingCartItem item = null;
            for(ShoppingCartItem scItems : items){
                
                if(scItems.getProduct().getProductId() == product.getProductId()){
                    
                    if(qty!=0){
                        scItems.setQuantity(qty);
                    }else{
                        item = scItems ;
                        break;
                    }
                    
                }
            }
            if(item!=null){
                items.remove(item);
            }
        }
        
    }

    public synchronized int getNumberOfItems(){
        numberOfItems = 0;
        
        for(ShoppingCartItem scItems : items){
            numberOfItems += scItems.getQuantity();
        }
        
        return numberOfItems;
    }
    
    public synchronized double getSubtotal(){
        
        double amount=0;
        
        for(ShoppingCartItem scItem:items){
            Product product = (Product) scItem.getProduct();
            amount += (scItem.getQuantity()* product.getProductPrice());
        }
        
        return amount;
    }
    
    //Subtotal miktarına belirlenen ek ücret ekler.Sonucu total fieldine günceller.
    public synchronized void getCalculateTotal(String surcharge){
        
        double amount = 0;
        double s = Double.parseDouble(surcharge);
        
        amount = this.getSubtotal();
        amount += s;
        total = amount;
    }
    
    //Tüm fieldleri sıfırlayarak kartı boşaltır.
    public synchronized void clear(){
        items.clear();
        numberOfItems =0;
        total = 0;
    }
}
