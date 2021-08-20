package com.interviewrepl.inventory.service;

import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.repository.InventoryRepository;
import com.interviewrepl.inventory.utililty.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InventoryServiceImplTest {

    @Autowired
    private InventoryService inventoryService;

    @MockBean
    private InventoryRepository repository;

    @Test
    public void testCreateInventory() {
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();
        when(repository.save(inventory)).thenReturn(inventory);
        assertThat(inventoryService.insert(inventory)).isEqualTo(inventory);
    }

    @Test
    public void testGetInventoryById() {
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();

        when(repository.findById(inventory.getStoreId())).thenReturn(java.util.Optional.of(inventory));
        assertThat(inventoryService.findByStoreId(inventory.getStoreId())).isEqualTo(inventory);
    }

    @Test
    public void testGetAllInventoryList() {
        Helper helper = getHelper();
        Inventory inventory1 = helper.buildInventory1();
        Inventory inventory2 = helper.buildInventory2();

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);
        inventoryList.add(inventory1);
        when(repository.findAll()).thenReturn(inventoryList);
        assertThat(inventoryService.findAllInventory()).isEqualTo(inventoryList);
    }

    @Test
    public void testDeleteInventory() {
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();

        when(repository.existsById(inventory.getStoreId())).thenReturn(true);
        assertTrue(inventoryService.delete(inventory.getStoreId()));
    }


    private Helper getHelper() {
        return new Helper();
    }
}