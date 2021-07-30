package com.interviewrepl.inventory.repository;

import com.interviewrepl.inventory.model.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Integer>
{
    Inventory findByStoreId(Integer storeId);
}

