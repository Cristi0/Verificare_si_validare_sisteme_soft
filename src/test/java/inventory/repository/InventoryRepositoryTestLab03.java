package inventory.repository;

import inventory.model.Inventory;
import inventory.model.Product;
import javafx.collections.FXCollections;
import org.junit.jupiter.api.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InventoryRepositoryTestLab03
{
    static private InventoryRepository inventoryRepository;
    static private Deque<Product> addedTestProducts = new ArrayDeque<>();//double ended queue to remove all parts added by tests
    static private List<Product> backupDatabase = new ArrayList<>();
    //ensuring that the file database contains the previous, valid data

    @BeforeAll
    static void initialize()
    {
        System.out.println("WhiteBoxTesting InventoryRepository class.");
        InventoryRepository inventoryRepository = new InventoryRepository();
        List<Product> products = inventoryRepository.getAllProducts();
        for (Product product : products)
            inventoryRepository.deleteProduct(product);
        backupDatabase.addAll(products);
    }

    @BeforeEach
    void setUp()
    {
        inventoryRepository = new InventoryRepository();
    }

    @AfterEach
    void tearDown()
    {
        while (addedTestProducts.size() > 0)
            inventoryRepository.deleteProduct(addedTestProducts.poll());
    }

    @AfterAll
    static void terminate()
    {
        inventoryRepository = new InventoryRepository();
        for (Product product : backupDatabase)
            inventoryRepository.addProduct(product);
    }

    @Test
    void lookupTest_nameMatchesFirstInListProduct_returnsProduct()
    {
        Product product = new Product(inventoryRepository.getAutoProductId(), "name0", 1.0, 2, 1, 2, FXCollections.observableArrayList() );
        inventoryRepository.addProduct(product);
        addedTestProducts.add(product);
        Assertions.assertEquals(product.getName(), inventoryRepository.lookupProduct(product.getName()).getName());
    }

    @Test
    void lookupTest_idMatchesFirstInListProduct_returnsProduct()
    {
        Product product = new Product(inventoryRepository.getAutoProductId(), "name0", 1.0, 2, 1, 2, FXCollections.observableArrayList());
        inventoryRepository.addProduct(product);
        addedTestProducts.add(product);
        Assertions.assertEquals(product.getProductId(),
                inventoryRepository.lookupProduct(String.valueOf(product.getProductId())).getProductId());
    }

    @Test
    void lookupTest_nameMatchesNonFirstInListProduct_returnsProduct()
    {
        Product firstDummyProduct = new Product(inventoryRepository.getAutoProductId(), "dummy", 1.0, 2, 1, 2, FXCollections.observableArrayList());
        Product product = new Product(inventoryRepository.getAutoProductId(), "name", 1.0, 2, 1, 2, FXCollections.observableArrayList());
        inventoryRepository.addProduct(firstDummyProduct);
        inventoryRepository.addProduct(product);
        addedTestProducts.add(firstDummyProduct);
        addedTestProducts.add(product);
        Assertions.assertEquals(product.getName(), inventoryRepository.lookupProduct(product.getName()).getName());
    }

    @Test
    void lookupTest_idMatchesNonFirstInListProduct_returnsProduct()
    {
        Product firstDummyProduct = new Product(inventoryRepository.getAutoProductId(), "dummy", 1.0, 2, 1, 2, FXCollections.observableArrayList());
        Product product = new Product(inventoryRepository.getAutoProductId(), "name", 1.0, 2, 1, 2, FXCollections.observableArrayList());
        inventoryRepository.addProduct(firstDummyProduct);
        inventoryRepository.addProduct(product);
        addedTestProducts.add(firstDummyProduct);
        addedTestProducts.add(product);
        Assertions.assertEquals(product.getProductId(),
                inventoryRepository.lookupProduct(String.valueOf(product.getProductId())).getProductId());
    }

    @Test
    void lookupTest_noMatchFoundInEmptyList_returnsNullNewProduct()
    {
        Product expected = new Product(0, null, 0.0, 0, 0, 0, null);
        Product actual = inventoryRepository.lookupProduct("");
        Assertions.assertEquals(expected.getProductId(), actual.getProductId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void lookupTest_noMatchFoundInNonEmptyList_returnsNull()
    {
        Product dummyProduct = new Product(inventoryRepository.getAutoProductId(), "dummy", 1.0, 2, 1, 2, FXCollections.observableArrayList());
        inventoryRepository.addProduct(dummyProduct);
        addedTestProducts.add(dummyProduct);
        Assertions.assertNull(inventoryRepository.lookupProduct("brcdbr"));
    }
}
