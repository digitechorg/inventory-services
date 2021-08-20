package com.interviewrepl.inventory.repository;

import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.utililty.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InventoryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    public void testSaveInventory(){
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();

        Inventory savedInDb = entityManager.merge(inventory);
        Inventory getFromDb = inventoryRepository.findByStoreId(savedInDb.getStoreId());
        assertThat(getFromDb).isEqualTo(savedInDb);
    }

    @Test
    public void testGetInventoryById(){
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();
        //Save Inventory in DB
        Inventory inventorySavedInDb = entityManager.merge(inventory);
        //Get Inventory from DB
        Inventory inventoryFromInDb = inventoryRepository.findByStoreId(inventorySavedInDb.getStoreId());
        assertThat(inventorySavedInDb).isEqualTo(inventoryFromInDb);
    }

    private Helper getHelper() {
        return new Helper();
    }
}
