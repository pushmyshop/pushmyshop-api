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

    @ManyToOne
    @JoinColumn(name="template_id")
    private Template template;

    @OneToMany(mappedBy = "compagny")
    private List<Product> products;
}
