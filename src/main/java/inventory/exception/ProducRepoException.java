package inventory.exception;

public class ProducRepoException implements InventoryException {
    private String message;

    public ProducRepoException(String message) {
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
