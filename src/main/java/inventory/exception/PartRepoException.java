package inventory.exception;

public class PartRepoException extends Exception implements InventoryException {
    private final String message;

    public PartRepoException(String message) {
        this.message = message;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
