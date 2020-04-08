package inventory.model;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest
{
    private Inventory inventory;

    @BeforeEach
    void setup()
    {
        inventory = new Inventory();
    }

    @Test
    void lookupTest_nameMatchesFirstInListProduct_returnsProduct()
    {
        Product product = new Product(inventory.getAutoProductId(),
                "name",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        inventory.addProduct(product);
        Assertions.assertEquals(product, inventory.lookupProduct(product.getName()));
    }

    @Test
    void lookupTest_idMatchesFirstInListProduct_returnsProduct()
    {
        Product product = new Product(inventory.getAutoProductId(),
                "name",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        inventory.addProduct(product);
        Assertions.assertEquals(product, inventory.lookupProduct(String.valueOf(product.getProductId())));
    }

    @Test
    void lookupTest_nameMatchesNonFirstInListProduct_returnsProduct()
    {
        Product dummy = new Product(inventory.getAutoProductId(),
                "dummy",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        Product product = new Product(inventory.getAutoProductId(),
                "name",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        inventory.addProduct(dummy);
        inventory.addProduct(product);
        Assertions.assertEquals(product, inventory.lookupProduct(product.getName()));
    }

    @Test
    void lookupTest_idMatchesNonFirstInListProduct_returnsProduct()
    {
        Product dummy = new Product(inventory.getAutoProductId(),
                "dummy",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        Product product = new Product(inventory.getAutoProductId(),
                "name",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        inventory.addProduct(dummy);
        inventory.addProduct(product);
        Assertions.assertEquals(product, inventory.lookupProduct(String.valueOf(product.getProductId())));
    }

    @Test
    void lookupTest_noMatchFoundInEmptyList_returnsNewProduct()
    {
        Product expected = new Product(0, null, 0.0, 0, 0, 0, null);
        Product actual = inventory.lookupProduct("random");
        Assertions.assertEquals(expected.getProductId(), actual.getProductId());
        Assertions.assertEquals(expected.getName(), actual.getName());
    }

    @Test
    void lookupTest_noMatchFoundInNonEmptyList_returnsNull()
    {
        Product dummy = new Product(inventory.getAutoProductId(),
                "dummy",
                1,
                1,
                1,
                10,
                FXCollections.observableArrayList());
        inventory.addProduct(dummy);
        assertNull(inventory.lookupProduct("random"));
    }
}