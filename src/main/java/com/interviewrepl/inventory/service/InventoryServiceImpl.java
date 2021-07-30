package com.interviewrepl.inventory.service;

import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    @Autowired
  private InventoryRepository repository;


    @Override
    public Inventory findByStoreId(Integer storeId) {
        Optional<Inventory> result = repository.findById(storeId);
        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Inventory> findAllInventory() {
        return (List<Inventory>)repository.findAll();
    }


    @Override
    public Inventory insert(Inventory inventory) {
        return repository.save(inventory);
    }

    @Override
    public List<Inventory> insertAll(List<Inventory> inventoryList) {
        return (List<Inventory>)repository.saveAll(inventoryList);
    }

    @Override
    public boolean update(Inventory inventory) {
        try {
            repository.save(inventory);
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateAll(List<Inventory> inventoryList) {
        try {
            repository.saveAll(inventoryList);
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer storeId) {
        try {
            repository.deleteById(storeId);
            return true;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
    }

}