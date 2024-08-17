package ecommersite.swiftshopper.entites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;


@Getter @Setter
@Entity @Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer productID; // The unique id of the product

    @Column(name = "Name")
    private String productName;

    @Column(name = "Description")
    private String productDescription;

    @Column(name = "Price")
    private double productPrice; // The price of the product (change as per region)

    @Column(name = "Currency")
    private String productCurrency; // The currency of the product (default: INR)

    @Column(name = "Quantity")
    private long productStockQuantity; // The quantity/units of product available at the moment

    @Column(name = "Refundable")
    private boolean productIsRefundable;

    @Column(name = "Returnable")
    private boolean productIsReturnable;

    @Column(name = "Years")
    private short productWarrantyYears;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Categoryid", referencedColumnName = "ID")
    @JsonManagedReference
    private Category category;
}
