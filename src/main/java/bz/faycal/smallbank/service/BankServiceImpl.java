package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.*;
import bz.faycal.smallbank.exception.BankAccountNotFoundException;
import bz.faycal.smallbank.repository.AccountRepository;
import bz.faycal.smallbank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
@Transactional
public class BankServiceImpl implements IBankService {

    private AccountRepository accountRepository;
    private OperationRepository operationRepository;

    @Autowired
    public BankServiceImpl(AccountRepository accountRepository,
                           OperationRepository operationRepository){
        this.accountRepository=accountRepository;
        this.operationRepository=operationRepository;
    }

    @Override
    public Account checkAccount(String accountCode) {
        return accountRepository.findById(accountCode)
                .orElseThrow(()-> new BankAccountNotFoundException("The asked account cloud'nt be found"));
    }

    @Override
    public void deposit(String accountCode, double amount) {
        Account account=checkAccount(accountCode);
        Deposit deposit=new Deposit(new Date(),amount,account);
        operationRepository.save(deposit);
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
    }

    @Override
    public void withdraw(String accountCode, double amount) {
        Account account=checkAccount(accountCode);
        double fc=0;
        if(account instanceof CurrentAccount)
            fc=((CurrentAccount)account).getOverdraft();
        if(account.getBalance()+fc<amount)
            throw new RuntimeException("insufficient balance");
        Withdrawal withdrawal=new Withdrawal(new Date(),amount,account);
        operationRepository.save(withdrawal);
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
    }

    @Override
    public Page<Operation> operationsList(String accountCode, int page, int size) {
        return operationRepository.findAllOperationByAccount(accountCode,new PageRequest(page,size));
    }
}
