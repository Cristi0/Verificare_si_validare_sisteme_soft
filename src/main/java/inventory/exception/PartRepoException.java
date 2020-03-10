package inventory.exception;

public class PartRepoException implements InventoryException {
    private String message;

    public PartRepoException(String message) {
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
