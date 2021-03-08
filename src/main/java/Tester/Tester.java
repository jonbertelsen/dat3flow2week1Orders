/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import entities.Customer;
import entities.OrderLine;
import entities.Orders;
import entities.Product;
import facade.CustomerFacade;
import facade.OrderFacade;
import facade.OrderLineFacade;
import facade.ProductFacade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

/**
 *
 * @author jobe
 */
public class Tester {
    
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    
    public static void main(String[] args) {
        
        EntityManager em = emf.createEntityManager();
        
        deleteAllTables(em);
     
        // Create a Customer
        System.out.println("Create a customer ************");
        Customer cust = CustomerFacade.createCustomer("Jon", "jobe@cpbusiness.dk");
        
        // Find a customer
        System.out.println("Find a customer ************");
        findCustomer(cust.getId());
        findCustomer(1212);
        
        // Get all customers
        System.out.println("Get all customers ************");
        getAllCustomers();
        
        // Create Products
        System.out.println("Create a product ************");
        Product p1 = ProductFacade.createProduct("Inov8 Trailtalon 250", "Fed trailsko",1195.00);
        Product p2 = ProductFacade.createProduct("Vivobarefoot SG", "Flad trailsko",895.00);
        
         // Find a product
        findProduct(p1.getId());
        findProduct(12);
        
        // Get and show all products (item types)
        getAllProducts(); 
        
        // Create an order and add to customer
        System.out.println("Add an emtpy order with an order_id ************");
        Orders o1 = OrderFacade.createOrder(new Orders());
        Orders o2 = OrderFacade.createOrder(new Orders());
        Customer c1 = CustomerFacade.createCustomer("Alfons Aaberg", "a@cpbusiness.dk");
        Customer c2 = CustomerFacade.createCustomer("Jørgen Jønke", "jj@ha.dk");
        CustomerFacade.addOrder(c1, o1);
        CustomerFacade.addOrder(c2, o2);
        
        // Create orderlines
        System.out.println("Add an orderline ************");
        OrderLine ol1 = OrderLineFacade.createOrderLine(p1,o1, 1);
        OrderLine ol2 = OrderLineFacade.createOrderLine(p2, o1, 2);
        OrderLine ol3 = OrderLineFacade.createOrderLine(p2, o2, 3);
        
        findAllOrdersAndSums();
    }

    // *********** Helper methods **********************
    
    private static void deleteAllTables(EntityManager em) {
        // Delete content in all tables
        em.getTransaction().begin();
        em.createNamedQuery("OrderLine.deleteAllRows").executeUpdate();
        em.createNamedQuery("Orders.deleteAllRows").executeUpdate();
        em.createNamedQuery("Customer.deleteAllRows").executeUpdate();
        em.createNamedQuery("Product.deleteAllRows").executeUpdate();
        em.getTransaction().commit();
    }
    
       private static void findCustomer(Integer customer_id) {
        try {
            Customer cust = CustomerFacade.findCustomerById(customer_id);
            System.out.println("Kunde med id = " + cust.getId() + " er fundet");
        } catch (EntityNotFoundException e){
            System.out.println("Kunde med id = " + customer_id + " findes ikke");
        }
    }
       
           private static void getAllCustomers() {
        List<Customer> customers = CustomerFacade.getAllCustomers();
        if (customers.size() > 0){
            for(Customer c: customers){
                System.out.printf("(%d,%s,%s)\n", c.getId(), c.getName(), c.getEmail());
            }
        } else {
            System.out.println("Listen af kunder er tom");
        }
    }
           
             private static void findProduct(Integer product_id) {
        System.out.println("Find a product ************");
        try {
            ProductFacade.findProductById(product_id);
            System.out.println("Product with id = " + product_id + " is found");
        } catch (EntityNotFoundException e){
            System.out.println("Product with id = " + product_id + " is NOT found");
        }
    }
    
    private static void getAllProducts() {
        // Get all products
        
        System.out.println("Get all products ************");
        List<Product> products = ProductFacade.getAllProducts();
        
        for(Product p: products){
            System.out.printf("(%d, %s, %s, %.2f)\n", p.getId(), p.getName(), p.getDescription(), p.getPrice());
        }
    }

      private static void findAllOrdersAndSums() throws EntityNotFoundException {
        // Find all orders
        System.out.println("Find all orders, orderlines and total sum of orders ************");
        List<Customer> allCustomers = CustomerFacade.getAllCustomers();
        for (Customer custObj: allCustomers){
            List<Orders> allOrders = CustomerFacade.getAllOrdersByCustomerID(custObj.getId());
            for (Orders orderObj: allOrders){
                System.out.printf("(OrderID: %d, Customer: %s)\n",orderObj.getId(), orderObj.getCustomer().getName());
                
                for (OrderLine olObj: orderObj.getOrderlines()){
                    System.out.printf("--- (Quantity: %d pcs of %s)\n", 
                            olObj.getQuantity(), olObj.getProduct().getName());
                }
                System.out.printf("Total order price: %.2f\n", OrderFacade.getOrderSum(orderObj.getId()) );
            }
        }
    }


 
    
    
}
