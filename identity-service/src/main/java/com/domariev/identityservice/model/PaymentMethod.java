package com.domariev.identityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id")
    private PaymentCard paymentCard;
    @Column(name = "active")
    private boolean isActive;
    @ManyToOne
    private User user;

}
