package ecommersite.swiftshopper.entites;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
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


    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}
