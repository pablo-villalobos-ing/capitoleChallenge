package cl.pablovillalobos.challenge.infrastructure.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
