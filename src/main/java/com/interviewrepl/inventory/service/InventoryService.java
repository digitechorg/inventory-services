package com.interviewrepl.inventory.service;

import com.interviewrepl.inventory.model.Inventory;

import java.util.List;

public interface InventoryService {

    // GET
    Inventory findByStoreId(Integer storeId);
    List<Inventory> findAllInventory();


    //POST
    Inventory insert(Inventory inventory);
    List<Inventory> insertAll(List<Inventory> inventoryList);

    // PUT
    boolean update(Inventory inventory);
    boolean updateAll(List<Inventory> inventoryList);

    // DELETE
    boolean delete(Integer storeId);
}
