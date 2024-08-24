package ecommersite.swiftshopper.entites;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter @Setter
@Entity @Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer productID; // The unique id of the product

    @Column(name = "Name")
    private String productName;

    @Column(name = "Description")
    private String productDescription;

    @Column(name = "Category")
    private String productCategory;

    @Column(name = "Brand")
    private String productBrand;

    @Column(name = "Price")
    private double productPrice; // The price of the product (change as per region)

    @Column(name = "Currency")
    private String productCurrency; // The currency of the product (default: INR)

    @Column(name = "Quantity")
    private long productStockQuantity; // The quantity/units of product available at the moment

    @Column(name = "Supplier")
    private String productSupplier;

    @Column(name = "Refundable")
    private boolean productIsRefundable;

    @Column(name = "Returnable")
    private boolean productIsReturnable;

    @Column(name = "Years")
    private short productWarrantyYears;

    @Column(name = "Available")
    private boolean productIsAvailable = true;
}
