/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author jobe
 */
@Embeddable
public class OrderLineId implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer product_id;
    private Integer order_id;

    public OrderLineId() {
    }

    public OrderLineId(Integer product_id, Integer order_id) {
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    
    
    @Override
    public int hashCode() {
        return  Objects.hash(product_id, order_id);
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
        final OrderLineId other = (OrderLineId) obj;
        if (!Objects.equals(this.product_id, other.product_id)) {
            return false;
        }
        if (!Objects.equals(this.order_id, other.order_id)) {
            return false;
        }
        return true;
    }
    
    
}
