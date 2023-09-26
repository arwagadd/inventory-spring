package com.example.inventory.model;

import com.example.inventory.enums.ItemStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item {
    @SequenceGenerator(
            name = "item_id_sequence",
            sequenceName = "item_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id_sequence"
    )
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "serial_number")
    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @Column(name = "asset_id", updatable = false , insertable = false)
    private Long assetId;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ItemStatus itemStatus  = ItemStatus.AVAILABLE;

    @Column(name = "average_rating")
    private String rating;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "description")
    private String description;
    //An item is associated with more than one user
    //A user can have many items
}
