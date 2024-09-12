package ecommersite.swiftshopper.entites;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@Entity
@Table(name = "product")
@Document(indexName = "products") // Elasticsearch index name
public class Product implements Serializable {

    @jakarta.persistence.Id // For JPA
    @Id // For Elasticsearch
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer productID; // The unique id of the product

    @Column(name = "Name")
    @Field(type = FieldType.Text)
    private String productName;

    @Column(name = "Description")
    @Field(type = FieldType.Text)
    private String productDescription;

    @Column(name = "Category")
    @Field(type = FieldType.Keyword)
    private String productCategory;

    @Column(name = "Brand")
    @Field(type = FieldType.Keyword)
    private String productBrand;

    @Column(name = "Price")
    @Field(type = FieldType.Double)
    private double productPrice; // The price of the product (change as per region)

    @Column(name = "Currency")
    @Field(type = FieldType.Keyword)
    private String productCurrency; // The currency of the product (default: INR)

    @Column(name = "Quantity")
    @Field(type = FieldType.Long)
    private long productStockQuantity; // The quantity/units of product available at the moment

    @Column(name = "Supplier")
    @Field(type = FieldType.Keyword)
    private String productSupplier;

    @Column(name = "Refundable")
    @Field(type = FieldType.Boolean)
    private boolean productIsRefundable;

    @Column(name = "Returnable")
    @Field(type = FieldType.Boolean)
    private boolean productIsReturnable;

    @Column(name = "Years")
    @Field(type = FieldType.Short)
    private short productWarrantyYears;

    @Column(name = "Available")
    @Field(type = FieldType.Boolean)
    private boolean productIsAvailable = true;

    // If you have other related entities like reviews, you can add them with @Field(type = FieldType.Nested)
    // private List<Review> reviews;
}
