package MockitoTests.UnitTests;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class InventoryRepositoryTest_Unit
{
    @Test
    void test_AddPartCallsAddPart_Repository()
    {
        Inventory mockedInventory = mock(Inventory.class);//mock inventory
        InventoryRepository inventoryRepository = spy(new InventoryRepository(mockedInventory));//use spy so we can mock only specific methods while the rest work as intended
        when(inventoryRepository.getAutoPartId()).thenReturn(0);
        doNothing().when(inventoryRepository).writeAll();//ignore write to file
        doNothing().when(mockedInventory).addPart(isA(Part.class));
        inventoryRepository.addPart(new InhousePart(inventoryRepository.getAutoPartId(), "Garnitura", 2.5, 11, 1, 15, 0));
        verify(mockedInventory, times(1)).addPart(isA(Part.class));//check if method was called once
        verify(inventoryRepository, times(1)).writeAll();//check if method was called once
    }

    @Test
    void test_DeletePartCallsDeletePart_Repository()
    {
        Inventory mockedInventory = mock(Inventory.class);//mock inventory
        InventoryRepository inventoryRepository = spy(new InventoryRepository(mockedInventory));//use spy so we can mock only specific methods while the rest work as intended
        when(inventoryRepository.getAutoPartId()).thenReturn(0);
        doNothing().when(inventoryRepository).writeAll();//ignore write to file
        doNothing().when(mockedInventory).deletePart(isA(Part.class));
        inventoryRepository.deletePart(new InhousePart(inventoryRepository.getAutoPartId(), "Garnitura", 2.5, 11, 1, 15, 0));
        verify(mockedInventory, times(1)).deletePart(isA(Part.class));//check if method was never called
        verify(inventoryRepository, times(1)).writeAll();//check if method was never called
    }
}