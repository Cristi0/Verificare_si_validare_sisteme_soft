package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import org.junit.jupiter.api.*;

import java.util.ArrayDeque;
import java.util.Deque;

class InventoryRepositoryTest
{
    private InventoryRepository inventoryRepository;
    private Deque<Part> addedTestParts = new ArrayDeque<>();

    @BeforeAll
    static void initialize()
    {
        System.out.println("Testing InventoryRepository class.");
    }


    @BeforeEach
    void setUp()
    {
        inventoryRepository = new InventoryRepository();
    }

    @AfterEach
    void tearDown()
    {
        while (addedTestParts.size() > 0)
            inventoryRepository.deletePart(addedTestParts.poll());
    }

    @Test
    void addPart_EC_TC1()
    {
        int previous = inventoryRepository.getAllParts().size();
        Part part = new OutsourcedPart(inventoryRepository.getAutoPartId(), "Garnitura", 2.5, 11, 1, 15, "Farmec");
        inventoryRepository.addPart(part);
        addedTestParts.add(part);
        Assertions.assertEquals(previous + 1, inventoryRepository.getAllParts().size());
    }

    @Test
    void addPart_EC_TC2()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "Joja", 10.5, -1, 1, 15, 1234);
        Assertions.assertThrows(RuntimeException.class, () -> inventoryRepository.addPart(part));
        addedTestParts.add(part);
    }

    @Test
    void addPart_EC_TC3()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "Timonerie", -150.5, 13, 1, 15, 1227);
        Assertions.assertThrows(RuntimeException.class, () ->inventoryRepository.addPart(part));
        addedTestParts.add(part);
    }
}