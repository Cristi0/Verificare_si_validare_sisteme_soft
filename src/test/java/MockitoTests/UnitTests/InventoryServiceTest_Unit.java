package MockitoTests.UnitTests;

import inventory.model.InhousePart;
import inventory.model.Part;
import inventory.repository.InventoryRepository;
import inventory.service.InventoryService;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class InventoryServiceTest_Unit
{
    @Test
    public void test_AddPartCallsAddPartRepository()
    {
        InventoryRepository mockedRepository = mock(InventoryRepository.class);//mock repository
        doNothing().when(mockedRepository).addPart(isA(Part.class));//prevent side effects from adding a part
        InventoryService inventoryService = new InventoryService(mockedRepository);
        inventoryService.addInhousePart("Garnitura", 2.3, 11, 1, 13, 0);
        verify(mockedRepository, times(1)).addPart(isA(Part.class));//check if addPart was called once
    }

    @Test
    public void test_DeletePartCallsDeletePartService()
    {
        InventoryRepository mockedRepository = mock(InventoryRepository.class);//mock repository
        doNothing().when(mockedRepository).deletePart(isA(Part.class));//prevent side effects from deleting a part
        InventoryService inventoryService = new InventoryService(mockedRepository);
        inventoryService.deletePart(new InhousePart(0, "Garnitura", 2.5, 11, 1, 15, 0));
        verify(mockedRepository, times(1)).deletePart(isA(Part.class));//check if deletePart was called once
    }
}