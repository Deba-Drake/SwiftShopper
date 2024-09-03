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
    @Column(name = "user_id")
    private Integer userID; // The unique id of the user

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false, unique = true)
    private String email; // The primary email address

    @Column(name = "alternate_mail", unique = true)
    private String alternateEmail; // Optional secondary email address

    @Column(name = "password", nullable = false)
    private String password; // The user's password (should be hashed)

    @Column(name = "phone_number")
    private String phoneNumber; // The user's phone number

    @Column(name = "location")
    private String location; // The user's location (city, state, etc.)

    @Column(name = "created", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // Timestamp for when the user was created

    @Column(name = "updated")
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
