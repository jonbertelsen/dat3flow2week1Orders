/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;

/**
 *
 * @author jobe
 */
@Entity
@NamedQuery(name = "OrderLine.deleteAllRows", query = "DELETE from OrderLine")
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
     @EmbeddedId
    private OrderLineId id;
    private int quantity;
    
    @ManyToOne() // Owning side (foreign key)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()   // Owning side (foreign key)
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Orders order;
    
    public OrderLine() {
    }
    
    public OrderLine(Product product, Orders order, int quantity) {
        this.quantity = quantity;
        this.id = new OrderLineId(product.getId(), order.getId());
        this.product = product;
        this.order = order;
        order.addOrderline(this);
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderLine other = (OrderLine) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
    
}
