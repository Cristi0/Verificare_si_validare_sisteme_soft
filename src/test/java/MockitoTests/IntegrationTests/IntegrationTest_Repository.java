package MockitoTests.IntegrationTests;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class IntegrationTest_Repository
{
    @Test
    public void test_AddPartCallsAddPartRepository()
    {
        Inventory mockedInventory = mock(Inventory.class);//mock inventory
        doNothing().when(mockedInventory).addPart(isA(Part.class));
        when(mockedInventory.getAutoPartId()).thenReturn(0);
        when(mockedInventory.getAllParts()).thenReturn(FXCollections.observableArrayList());
        when(mockedInventory.getProducts()).thenReturn(FXCollections.observableArrayList());

        InventoryRepository inventoryRepository = spy(new InventoryRepository(mockedInventory));//use spy so we can check method calls
        InventoryService inventoryService = new InventoryService(inventoryRepository);
        inventoryService.addInhousePart("Garnitura", 2.3, 11, 1, 13, 0);

        verify(inventoryRepository, times(1)).addPart(isA(Part.class));//check if addPart was called once
        verify(mockedInventory, times(1)).addPart(isA(Part.class));//check if method was called once
        verify(inventoryRepository, times(1)).writeAll();//check if method was called once
    }

    @Test
    public void test_DeletePartCallsDeletePart_Repository()
    {
        Inventory mockedInventory = mock(Inventory.class);//mock inventory
        doNothing().when(mockedInventory).deletePart(isA(Part.class));
        when(mockedInventory.getAutoPartId()).thenReturn(0);
        when(mockedInventory.getAllParts()).thenReturn(FXCollections.observableArrayList());
        when(mockedInventory.getProducts()).thenReturn(FXCollections.observableArrayList());

        InventoryRepository inventoryRepository = spy(new InventoryRepository(mockedInventory));//use spy so we can mock only specific methods while the rest work as intended
        InventoryService inventoryService = new InventoryService(inventoryRepository);
        inventoryService.deletePart(new InhousePart(0, "Garnitura", 2.5, 11, 1, 15, 0));

        verify(inventoryRepository, times(1)).deletePart(isA(Part.class));//check if deletePart was called once
        verify(inventoryRepository, times(1)).writeAll();//check if method was called once
        verify(mockedInventory, times(1)).deletePart(isA(Part.class));//check if method was called once
    }
}
