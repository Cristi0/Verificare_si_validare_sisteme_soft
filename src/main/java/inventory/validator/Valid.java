package inventory.validator;

import inventory.model.Part;
import javafx.collections.ObservableList;

public class Valid {
    private static Valid valid=null;

    private Valid() {
    }

    public static Valid getValid() {
        if(valid==null)
            valid = new Valid();
        return valid;
    }

    public static String isValidProduct(String name, double price, int inStock, int min, int max, ObservableList<Part> parts, String errorMessage) {
        return null;
    }

    public static String isValidPart(String name, double price, int inStock, int min, int max, String errorMessage) {
        return null;
    }
}
