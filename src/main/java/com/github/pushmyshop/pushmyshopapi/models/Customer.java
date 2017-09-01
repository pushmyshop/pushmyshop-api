package com.github.pushmyshop.pushmyshopapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String mail;
    private String username;
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Compagny> compagnies;


}
