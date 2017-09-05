package com.github.pushmyshop.pushmyshopapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String desc;
    private Double price;

    @ManyToOne
    @JoinColumn(name="compagny_id")
    @JsonIgnore
    private Compagny compagny;

}
