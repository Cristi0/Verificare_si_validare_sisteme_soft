package inventory.repository;

import inventory.model.InhousePart;
import inventory.model.OutsourcedPart;
import inventory.model.Part;
import org.junit.jupiter.api.*;

import java.util.ArrayDeque;
import java.util.Collections;
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
    void addPart_ECP_TC1()
    {
        int previous = inventoryRepository.getAllParts().size();
        Part part = new OutsourcedPart(inventoryRepository.getAutoPartId(), "Garnitura", 2.5, 11, 1, 15, "Farmec");
        inventoryRepository.addPart(part);
        addedTestParts.add(part);
        Assertions.assertEquals(previous + 1, inventoryRepository.getAllParts().size());
    }

    @Test
    void addPart_ECP_TC2()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "Joja", 10.5, -1, 1, 15, 1234);
        Assertions.assertThrows(RuntimeException.class,
                () -> inventoryRepository.addPart(part),
                "Inventory level must be greater than 0. ");
        addedTestParts.add(part);
    }

    @Test
    void addPart_ECP_TC3()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "Timonerie", -150.5, 13, 1, 15, 1227);
        Assertions.assertThrows(RuntimeException.class,
                () -> inventoryRepository.addPart(part),
                "The price must be greater than 0. ");
        addedTestParts.add(part);
    }

    @Test
    void addPart_ECP_TC5()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "", 1.5, 15, 1, 15, 2211);
        Assertions.assertThrows(RuntimeException.class,
                () -> inventoryRepository.addPart(part),
                "A name has not been entered. ");
        addedTestParts.add(part);
    }

    @Test
    void addPart_BVA_TC01()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "", 10.5, 1, 1, 15, 1234);
        Assertions.assertThrows(RuntimeException.class,
                () -> inventoryRepository.addPart(part),
                "A name has not been entered. ");
        addedTestParts.add(part);
    }

    @Test
    void addPart_BVA_TC03()
    {
        int previous = inventoryRepository.getAllParts().size();
        Part part = new OutsourcedPart(inventoryRepository.getAutoPartId(), "M", 5.5, 11, 1, 15, "Company1");
        inventoryRepository.addPart(part);
        addedTestParts.add(part);
        Assertions.assertEquals(previous + 1, inventoryRepository.getAllParts().size());
    }

    @Test
    void addPart_BVA_TC04()
    {
        int previous = inventoryRepository.getAllParts().size();
        Part part = new InhousePart(inventoryRepository.getAutoPartId(), "Mb", 10, 15, 1, 15, 2221);
        inventoryRepository.addPart(part);
        addedTestParts.add(part);
        Assertions.assertEquals(previous + 1, inventoryRepository.getAllParts().size());
    }

    @Test
    void addPart_BVA_TC05()
    {
        int previous = inventoryRepository.getAllParts().size();
        Part part = new OutsourcedPart(inventoryRepository.getAutoPartId(),
                "M" + String.join("", Collections.nCopies(254, "a")),
                100,
                15,
                1,
                15,
                "Company1");
        inventoryRepository.addPart(part);
        addedTestParts.add(part);
        Assertions.assertEquals(previous + 1, inventoryRepository.getAllParts().size());
    }

    @Test
    void addPart_BVA_TC06()
    {
        int previous = inventoryRepository.getAllParts().size();
        Part part = new InhousePart(inventoryRepository.getAutoPartId(),
                "M" + String.join("", Collections.nCopies(253, "a")),
                100,
                10,
                1,
                15,
                2221);
        inventoryRepository.addPart(part);
        addedTestParts.add(part);
        Assertions.assertEquals(previous + 1, inventoryRepository.getAllParts().size());
    }

    @Test
    void addPart_BVA_TC07()
    {
        Part part = new InhousePart(inventoryRepository.getAutoPartId(),
                "M" + String.join("", Collections.nCopies(255, "a")),
                10.5,
                1,
                1,
                15,
                1234);
        Assertions.assertThrows(RuntimeException.class,
                () -> inventoryRepository.addPart(part),
                "Name cannot be longer than 255. ");
        addedTestParts.add(part);
    }
}