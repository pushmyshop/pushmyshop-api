package com.github.pushmyshop.pushmyshopapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Compagny {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    private String name;

    private String template;

    private Boolean isGenerated = false;

    @OneToMany(mappedBy = "compagny", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Product> products;

    @OneToOne(cascade=CascadeType.ALL)
    private PushSubscription subscription;

    private String title;
    private String subTitle;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String postalCode;
    private String town;
    private String telephoneNumber;
    private String emailAddress;
    private String image;
    private String logo;

}
