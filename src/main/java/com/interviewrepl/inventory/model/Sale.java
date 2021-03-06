package com.interviewrepl.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "Sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "ItemName can not be null")
    @Size(max = 40, message = "ItemName cannot be more than 40 characters")
    @Column(name = "itemName")
    @JsonProperty("ItemName")
    public String itemName;

    @Size(min = 12, max = 12, message = ("ItemId length must be of 12 characters"))
    @Column(name = "itemId")
    @JsonProperty("ItemId")
    public long itemId;

    @Column(name = "quantity")
    @JsonProperty("Quantity")
    public int quantity;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
