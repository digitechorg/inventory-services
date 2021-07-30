package com.interviewrepl.inventory.controller;


import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/")
@RestController
public class InventoryController {

    private static final String REQUEST_NO_BODY = "Request does not contain a body";
    Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    InventoryService inventoryService;

    @GetMapping("/allinventory")
    private List<Inventory> getAllInventory() {
        return inventoryService.findAllInventory();
    }

    @GetMapping("/inventory/{storeId}")
    private Inventory getInventoryByStoreId(@PathVariable("storeId") Integer storeId) {
        return inventoryService.findByStoreId(storeId);
    }


    @PostMapping("/inventory")
    private String saveInventory(@RequestBody Inventory inventory) {
        if (inventory != null) {
            inventoryService.insert(inventory);
            return "Added a inventory";
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @PostMapping("/inventory/bulk")
    public String addInventoryInBulk(@RequestBody List<Inventory> inventoryList) {
        if(inventoryList != null && !inventoryList.isEmpty()) {
            inventoryService.insertAll(inventoryList);
            return String.format("Added %d inventory in bulk", inventoryList.size());
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @DeleteMapping("/inventory/{storeId}")
    public String deleteInventory(@PathVariable("storeId") int storeId) {
        if(storeId > 0) {
            if(inventoryService.delete(storeId)) {
                return "Deleted the inventory.";
            } else {
                return "Cannot delete the inventory.";
            }
        }
        return "The storeId is invalid for the inventory.";
    }

    @DeleteMapping("/inventory/bulk")
    public String deleteInventoryInBulk(@RequestBody List<Inventory> inventoryList) {
        if(!inventoryList.isEmpty()) {
            if(inventoryService.deleteAll(inventoryList)) {
                return "Deleted the inventoryList.";
            } else {
                return "Cannot delete the inventoryList.";
            }
        }
        return "The request should contain a list of inventoryList to be deleted.";
    }

    @PutMapping("/inventory")
    public String updateInventory(@RequestBody Inventory inventory) {
        if(inventory != null) {
            inventoryService.update(inventory);
            return "Updated inventory";
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @PutMapping("/inventory/bulk")
    public String updateInventoryInBulk(@RequestBody List<Inventory> inventoryList) {
        if(inventoryList != null) {
            inventoryService.updateAll(inventoryList);
            return "Updated inventoryList.";
        } else {
            return REQUEST_NO_BODY;
        }
    }

}

