package com.interviewrepl.inventory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Component
@Entity
@Table(name="INVENTORY")
public class Inventory {

    @Id
    @Column(name ="storeId" )
    @NotNull(message = ("StoreId cannot be null"))
    @Size(min=5,max=5, message = ("StoreId must be of size 5 characters"))
    @JsonProperty("StoreId")
    public int storeId;

    @OneToMany(targetEntity = Refund.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "storeId_fk", referencedColumnName = "storeId")
    @JsonProperty("Refund")
    public List<Refund> refund;

    @OneToMany(targetEntity = Delivery.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "storeId_fk", referencedColumnName = "storeId")
    @JsonProperty("Delivery")
    public List<Delivery> delivery;

    @OneToMany(targetEntity = Sale.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "storeId_fk", referencedColumnName = "storeId")
    @JsonProperty("Sale")
    public List<Sale> sale;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public List<Refund> getRefund() {
        return refund;
    }

    public void setRefund(List<Refund> refund) {
        this.refund = refund;
    }

    public List<Delivery> getDelivery() {
        return delivery;
    }

    public void setDelivery(List<Delivery> delivery) {
        this.delivery = delivery;
    }

    public List<Sale> getSale() {
        return sale;
    }

    public void setSale(List<Sale> sale) {
        this.sale = sale;
    }
}
