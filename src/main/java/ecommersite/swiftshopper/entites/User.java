package ecommersite.swiftshopper.entites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userID; // The unique id of the user

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Email", nullable = false, unique = true)
    private String email; // The primary email address

    @Column(name = "AlternateEmail", unique = true)
    private String alternateEmail; // Optional secondary email address

    @Column(name = "Password", nullable = false)
    private String password; // The user's password (should be hashed)

    @Column(name = "PhoneNumber")
    private String phoneNumber; // The user's phone number

    @Column(name = "Location")
    private String location; // The user's location (city, state, etc.)

    @Column(name = "Created", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Timestamp for when the user was created

    @Column(name = "Updated")
    private LocalDateTime updatedAt = LocalDateTime.now(); // Timestamp for the last update

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
