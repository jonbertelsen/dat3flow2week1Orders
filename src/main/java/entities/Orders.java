/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author jobe
 */
@Entity
@NamedQuery(name = "Orders.deleteAllRows", query = "DELETE from Orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer order_id;
    
    @ManyToOne()
    private Customer customer;
    
    @OneToMany(mappedBy="order", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private List<OrderLine> orderlines = new ArrayList();
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;

    public Integer getId() {
        return order_id;
    }

    public Orders() {
        this.orderDate = new Date();
    }

    public void setId(Integer id) {
        this.order_id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (order_id != null ? order_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.order_id == null && other.order_id != null) || (this.order_id != null && !this.order_id.equals(other.order_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orders[ id=" + order_id + " ]";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderlines() {
        return orderlines;
    }

    public void addOrderline (OrderLine orderline) {
        if (orderline != null){
            this.orderlines.add(orderline);
            orderline.setOrder(this); // update bidirectionality
        }
    }
    
    public void removeOrderLine(OrderLine orderline){
        if (orderline != null){
            orderlines.remove(orderline);
            orderline.setOrder(null);
        }
    }
    
}
