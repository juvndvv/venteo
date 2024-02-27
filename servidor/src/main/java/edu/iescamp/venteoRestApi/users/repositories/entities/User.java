package edu.iescamp.venteoRestApi.users.repositories.entities;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    private Date bornDate;

    private String imageUrl;

    private Timestamp createdAt;

    private Long roleId;
}
