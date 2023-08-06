package pl.matrasbartosz.parcellockersystem.exceptions.jwt;

public class UsernameAndPasswordAuthenticationRequestParseException extends RuntimeException {

    public UsernameAndPasswordAuthenticationRequestParseException(String message) {
        super(message);
    }
}
