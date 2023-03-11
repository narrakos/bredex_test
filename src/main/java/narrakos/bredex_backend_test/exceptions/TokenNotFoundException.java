package narrakos.bredex_backend_test.exceptions;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("Token does not exist");
    }
}
