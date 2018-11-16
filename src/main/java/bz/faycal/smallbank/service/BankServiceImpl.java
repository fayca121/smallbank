package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.entities.Operation;
import org.springframework.data.domain.Page;

public class BankServiceImpl implements IBankService {
    @Override
    public Account checkAccount(String accountCode) {
        return null;
    }

    @Override
    public void transfer(String accountCode, double amount) {

    }

    @Override
    public void withdraw(String accountCode, double amount) {

    }

    @Override
    public Page<Operation> operationsList(String accountCode, int page, int size) {
        return null;
    }
}
