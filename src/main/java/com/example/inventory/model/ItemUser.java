package com.example.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_user")
public class ItemUser {

    @SequenceGenerator(
            name = "item_user_id_sequence",
            sequenceName = "item_user_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_user_id_sequence"
    )
    @Id
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id" , updatable = false , insertable = false)
    private int userId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_id" , updatable = false , insertable = false)
    private int itemId;

}
