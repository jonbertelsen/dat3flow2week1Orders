package entities;

import entities.OrderLine;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-03-08T12:21:03")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile ListAttribute<Product, OrderLine> orderLines;
    public static volatile SingularAttribute<Product, Double> price;
    public static volatile SingularAttribute<Product, Integer> product_id;
    public static volatile SingularAttribute<Product, String> name;
    public static volatile SingularAttribute<Product, String> description;

}