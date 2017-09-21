package com.github.pushmyshop.pushmyshopapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Cart {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id = UUID.randomUUID();
    private State state = State.NOT_VALIDATED;

    @ManyToOne
    @JoinColumn(name="compagny_id")
    @JsonIgnore
    private Compagny compagny;

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    private String pickingDate;
    private String pickingHour;
    private String pickingName;
    private String pickingPhone;

    @OneToOne(cascade=CascadeType.ALL)
    private PushSubscription subscription;

    public void getPickingInformation(Cart cartToCheckout) {
        this.setPickingDate(cartToCheckout.getPickingDate());
        this.setPickingHour(cartToCheckout.getPickingHour());
        this.setPickingName(cartToCheckout.getPickingName());
        this.setPickingPhone(cartToCheckout.getPickingPhone());
    }

    public enum State {
        NOT_VALIDATED,
        VALIDATED,
        CONFIRMED,
        CANCELED
    }
}
