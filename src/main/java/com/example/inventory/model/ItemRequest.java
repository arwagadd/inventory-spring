package com.example.inventory.model;


import com.example.inventory.enums.ItemRequestType;
import com.example.inventory.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_request")
public class ItemRequest {

    //change int to Long (bigger) :  Done
    //Item Request type (enum): Replacement, New Item, Upgrade (Maintenance table) : Done
    //Statistics (weekly, monthly reports) about accepted/rejected requests, average items of users

    @SequenceGenerator(
            name = "item_request_id_sequence",
            sequenceName = "item_request_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_request_id_sequence"
    )
    @Id
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_id", updatable = false , insertable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "item_id", updatable = false , insertable = false)
    private Long itemId;

    @Column(name = "upgrade_id")
    private Long itemUpgradeId;

    @Column(name = "request_date_time")
    private LocalDateTime requestDateTime = LocalDateTime.now();

    @Column(name = "request_status")
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus = RequestStatus.Pending;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "request_type")
    @Enumerated(value = EnumType.STRING)
    private ItemRequestType itemRequestType;

}
