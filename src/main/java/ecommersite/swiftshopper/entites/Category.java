package ecommersite.swiftshopper.entites;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Entity @Table(name = "category")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer categoryID; //The unique id of the product

    @Column(name = "Name")
    private String categoryName;

    @Column(name = "Description")
    private String categoryDescription;

    @Column(name = "Available")
    private boolean categoryIsAvailable = true;
}
