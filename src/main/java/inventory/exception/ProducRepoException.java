package inventory.exception;

public class ProducRepoException extends Exception implements InventoryException {
    private final String message;

    public ProducRepoException(String message) {
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
