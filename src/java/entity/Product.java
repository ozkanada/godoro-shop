/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author GOGO
 */
@Entity
@Table(catalog="javashopdb")
public class Product implements Serializable{
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ProductId")
    private long productId;
    @Column(name="ProductName")
    private String productName;

    public Product() {
    }
    
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    
}
