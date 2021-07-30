package com.interviewrepl.inventory.controller;


import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/")
@RestController
public class InventoryController {

    private static final String REQUEST_NO_BODY = "Request does not contain a body";
    Logger logger = LoggerFactory.getLogger(InventoryController.class);

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(path = "/allinventory",produces = {
            MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<List<Inventory>> getAllInventory() {
        return new ResponseEntity<List<Inventory>>(inventoryService.findAllInventory(), HttpStatus.OK);

    }

    @GetMapping(path = "/inventory/{storeId}",produces = {
            MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<Inventory> getInventoryByStoreId(@PathVariable("storeId") Integer storeId) {
        if (storeId != null) {
            return new ResponseEntity<Inventory>(inventoryService.findByStoreId(storeId), HttpStatus.OK);
        } else {
            return new ResponseEntity<Inventory>(HttpStatus.NO_CONTENT);
        }
    }


    @PostMapping(path = "/inventory", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<Inventory> saveInventory( @RequestBody  @Valid Inventory inventory) {

        if (inventory != null) {
            inventoryService.insert(inventory);
            return new ResponseEntity<Inventory>(inventoryService.insert(inventory), HttpStatus.OK);
        } else {
            return new ResponseEntity<Inventory>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(path = "/inventory/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Inventory>> addInventoryInBulk(@RequestBody List<Inventory> inventoryList) {
        if (inventoryList != null && !inventoryList.isEmpty()) {
            inventoryService.insertAll(inventoryList);
            return new ResponseEntity<List<Inventory>>(inventoryService.insertAll(inventoryList), HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Inventory>>(HttpStatus.NO_CONTENT);
        }
    }


    @PutMapping(path = "/inventory", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateInventory(@Valid @RequestBody Inventory inventory) {
        if (inventory != null) {
            inventoryService.update(inventory);
            return "Updated inventory";
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @PutMapping(path = "/inventory/bulk", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String updateInventoryInBulk(@RequestBody List<Inventory> inventoryList) {
        if (inventoryList != null) {
            inventoryService.updateAll(inventoryList);
            return "Updated inventoryList.";
        } else {
            return REQUEST_NO_BODY;
        }
    }

    @DeleteMapping(path = "/inventory/{storeId}")
    public String deleteInventory(@PathVariable("storeId") int storeId) {
        if (storeId > 0) {
            if (inventoryService.delete(storeId)) {
                return "Deleted the inventory.";
            } else {
                return "Cannot delete the inventory.";
            }
        }
        return "The storeId is invalid for the inventory.";
    }

}

