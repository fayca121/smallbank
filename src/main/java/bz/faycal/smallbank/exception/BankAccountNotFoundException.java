package bz.faycal.smallbank.exception;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String s) {
        super(s);
    }
}
