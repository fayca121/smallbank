package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBankService {
    Account checkAccount(String accountCode);
    void transfer(String accountCode,double amount);
    void withdraw(String accountCode,double amount);
    Page<Operation> operationsList(String accountCode,int page,int size);

}
