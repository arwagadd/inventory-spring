package com.example.inventory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "maintenance")
public class Maintenance {
    @SequenceGenerator(
            name = "maintenance_id_sequence",
            sequenceName = "maintenance_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "maintenance_id_sequence"
    )
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_id", updatable = false , insertable = false)
    private int itemId;

    @Column(name = "action")
    private String action;

    @Column(name = "price")
    private int price;

    @Column(name = "start_date")
    private LocalDateTime startTime;

    @Column(name = "end_date")
    private LocalDateTime endTime;

}
