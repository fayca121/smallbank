package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.Account;
import bz.faycal.smallbank.entities.Client;
import bz.faycal.smallbank.exception.ClientNotFoundException;
import bz.faycal.smallbank.repository.AccountRepository;
import bz.faycal.smallbank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public CustomerServiceImpl(ClientRepository clientRepository,
                               AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository= accountRepository;
    }

    @Override
    public Client checkClient(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(()->new ClientNotFoundException("Client not found"));
    }

    @Override
    public Client checkClient(Long code) {
        return clientRepository.findById(code)
                .orElseThrow(()->new ClientNotFoundException("Client not found"));
    }

    @Override
    public void deleteClient(Long code) {
        Client client=checkClient(code);
        if(client.getAccounts()!=null && !client.getAccounts().isEmpty()) {
            if (!client.isSuspended()) {
                List<Account> activeAccounts = client.getAccounts().stream()
                        .filter(c -> !c.getSuspended())
                        .collect(Collectors.toList());
                activeAccounts.forEach(c -> c.setSuspended(true));
                accountRepository.saveAll(activeAccounts);
            }
        }

        else
            clientRepository.delete(client);

    }

    @Override
    public Client saveClient(Client c) {
        return clientRepository.save(c);
    }

    @Override
    public Client updateClient(Client c) {
        return saveClient(c);
    }

    @Override
    public Page<Client> clientList(int page, int size) {
        return clientRepository.findAll(PageRequest.of(page,size));
    }
}
