package MockitoTests.UnitTests;

import inventory.model.InhousePart;
import inventory.model.Inventory;
import inventory.model.Part;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
class InventoryTest_Unit
{

    @Test
    public void test_AddPartCallsAddPart_Entity()
    {
        ObservableList<Part> parts = spy(FXCollections.observableArrayList());
        Inventory inventory = new Inventory(null, parts, 0,0);
        inventory.addPart(new InhousePart(0, "Garnitura", 2.5, 11, 1, 15, 0));
        verify(parts, times(1)).add(isA(Part.class));//check if addPart was called once
    }

    @Test
    public void test_DeletePartCallsDeletePart_Entity()
    {
        ObservableList<Part> parts = spy(FXCollections.observableArrayList());
        Inventory inventory = new Inventory(null, parts, 0,0);
        inventory.deletePart(new InhousePart(0, "Garnitura", 2.5, 11, 1, 15, 0));
        verify(parts, times(1)).remove(isA(Part.class));//check if deletePart was called once
    }
}