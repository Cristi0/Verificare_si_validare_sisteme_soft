package MockitoTests.IntegrationTests;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class IntegrationTest_Service
{
    @Test
    public void test_AddPartCallsAddPartService()
    {
        Inventory inventory = spy(new Inventory());//using spy to check if method was called
        InventoryRepository inventoryRepository = spy(new InventoryRepository(inventory));
        InventoryService inventoryService = new InventoryService(inventoryRepository);
        inventoryService.addInhousePart("Garnitura", 2.3, 11, 1, 13, 0);

        verify(inventoryRepository, times(1)).addPart(isA(Part.class));//check if addPart was called once
        verify(inventory, times(1)).addPart(isA(Part.class));//check if addPart was called once
        verify(inventoryRepository, times(1)).writeAll();//check if writeAll was called once
    }

    @Test
    public void test_DeletePartCallsDeletePartService()
    {
        Inventory inventory = spy(new Inventory());//using spy to check if method was called
        InventoryRepository inventoryRepository = spy(new InventoryRepository(inventory));
        InventoryService inventoryService = new InventoryService(inventoryRepository);
        inventoryService.deletePart(new InhousePart(0, "Garnitura", 2.5, 11, 1, 15, 0));

        verify(inventoryRepository, times(1)).deletePart(isA(Part.class));//check if deletePart was called once
        verify(inventory, times(1)).deletePart(isA(Part.class));//check if deletePart was called once
        verify(inventoryRepository, times(1)).writeAll();//check if writeAll was called once
    }
}
