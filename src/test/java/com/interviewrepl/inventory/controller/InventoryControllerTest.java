package com.interviewrepl.inventory.controller;

import com.interviewrepl.inventory.model.Inventory;
import com.interviewrepl.inventory.service.InventoryService;
import com.interviewrepl.inventory.utililty.Helper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InventoryService inventoryService;

    @InjectMocks
    private InventoryController controller;


    @Test
    public void whenPOSTIsCalledThenAInventoryIsCreated() throws Exception {
        Helper helper = getHelper();
        Inventory inventory = helper.buildInventory1();
        String inputInJson = helper.mapToJson(inventory);
        String URI = "/api/inventory";
        when(inventoryService.insert(Mockito.any(Inventory.class))).thenReturn(inventory);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void whenPOSTIsCalledForInventoryInBulkThenAInventoryIsCreatedInBulk() throws Exception {
        Helper helper = getHelper();
        Inventory inventory1 = helper.buildInventory1();
        Inventory inventory2 = helper.buildInventory2();

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);
        inventoryList.add(inventory1);
        String inputInJson = helper.mapToJson(inventoryList);
        when(inventoryService.insertAll(inventoryList)).thenReturn(inventoryList);

        String URI = "/api/inventory/bulk";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String expectedJson = helper.mapToJson(inventoryList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void testGetInventoryById() throws Exception {
        Helper helper = getHelper();
        Inventory inventory1 = helper.buildInventory1();

        when(inventoryService.findByStoreId(Mockito.anyInt())).thenReturn(inventory1);
        String URI = "/api/inventory/99305";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = helper.mapToJson(inventory1);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void testGetAllInventoryList() throws Exception {

        Helper helper = getHelper();
        Inventory inventory1 = helper.buildInventory1();
        Inventory inventory2 = helper.buildInventory2();

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);
        inventoryList.add(inventory1);
        String inputInJson = helper.mapToJson(inventoryList);

        Mockito.when(inventoryService.findAllInventory()).thenReturn(inventoryList);

        String URI = "/api/allinventory";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = helper.mapToJson(inventoryList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void testDeleteInventory() throws Exception {

        Helper helper = getHelper();
        Inventory inventory1 = helper.buildInventory1();
        String URI = "/api/inventory/99305";
        when(inventoryService.delete(inventory1.getStoreId())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
                URI);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String output = result.getResponse().getContentAsString();
        assertEquals(output,"Deleted the inventory.");
    }


    private Helper getHelper() {
        return new Helper();
    }

}