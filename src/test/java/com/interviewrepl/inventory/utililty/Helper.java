package com.interviewrepl.inventory.utililty;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interviewrepl.inventory.model.Delivery;
import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.model.Refund;
import com.interviewrepl.inventory.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class Helper {

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     */
    public String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }


    // Create first Inventory object
    public Inventory buildInventory1() {
        Inventory inventory = new Inventory();
        List<Refund> refundList = new ArrayList<>();
        List<Sale> saleList = new ArrayList<>();
        List<Delivery> deliveryList = new ArrayList<>();
        Refund refund = new Refund();
        Delivery delivery = new Delivery();
        Sale sale = new Sale();
        inventory.setStoreId(99305);
        refund.setItemId(728392017342l);
        refund.setItemName("T-shirt");
        refund.setQuantity(2);
        refund.setId(1l);
        refundList.add(refund);
        sale.setItemId(728392017342l);
        sale.setItemName("T-shirt");
        sale.setQuantity(4);
        sale.setId(1);
        saleList.add(sale);
        delivery.setItemId(728392017342l);
        delivery.setItemName("T-shirt");
        delivery.setQuantity(3);
        delivery.setId(1l);
        deliveryList.add(delivery);
        inventory.setRefund(refundList);
        inventory.setDelivery(deliveryList);
        inventory.setSale(saleList);
        return inventory;
    }


    // Create second Inventory object
    public Inventory buildInventory2() {
        Inventory inventory = new Inventory();
        List<Refund> refundList = new ArrayList<>();
        List<Sale> saleList = new ArrayList<>();
        List<Delivery> deliveryList = new ArrayList<>();
        Refund refund = new Refund();
        Delivery delivery = new Delivery();
        Sale sale = new Sale();
        inventory.setStoreId(99306);
        refund.setItemId(728392017343l);
        refund.setItemName("T-shirt");
        refund.setQuantity(20);
        refund.setId(2l);
        refundList.add(refund);
        sale.setItemId(728392017343l);
        sale.setItemName("T-shirt");
        sale.setQuantity(40);
        sale.setId(2);
        saleList.add(sale);
        delivery.setItemId(728392017343l);
        delivery.setItemName("T-shirt");
        delivery.setQuantity(30);
        delivery.setId(2l);
        deliveryList.add(delivery);
        inventory.setRefund(refundList);
        inventory.setDelivery(deliveryList);
        inventory.setSale(saleList);
        return inventory;
    }





}
