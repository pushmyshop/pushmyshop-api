package com.github.pushmyshop.pushmyshopapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private String desc;
    private Double price;
    private String image;

    @ManyToOne
    @JoinColumn(name="compagny_id")
    @JsonIgnore
    private Compagny compagny;

}
