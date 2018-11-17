package bz.faycal.smallbank;

import bz.faycal.smallbank.entities.*;
import bz.faycal.smallbank.repository.AccountRepository;
import bz.faycal.smallbank.repository.ClientRepository;
import bz.faycal.smallbank.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootLoader implements CommandLineRunner {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OperationRepository operationRepository;

    @Override
    public void run(String... args) throws Exception {
        Client c1=clientRepository.save(new Client("Faycal","bz.faycal@gmail.com"));
        Client c2=clientRepository.save(new Client("Wassila","wassou@gmail.com"));

        Account a1=accountRepository.save(new CurrentAccount("A1",new Date(),90000,c1,
                60000));
        Account a2=accountRepository.save(new SavingAccount("A2",new Date(),50000,c2,
                5.5));

        operationRepository.save(new Withdrawal(new Date(),9000,a1));
        operationRepository.save(new Withdrawal(new Date(),4000,a1));
        operationRepository.save(new Withdrawal(new Date(),5000,a1));
        operationRepository.save(new Deposit(new Date(),1000,a1));

        operationRepository.save(new Withdrawal(new Date(),9000,a2));
        operationRepository.save(new Withdrawal(new Date(),4000,a2));
        operationRepository.save(new Withdrawal(new Date(),5000,a2));
        operationRepository.save(new Deposit(new Date(),1000,a2));



    }
}
