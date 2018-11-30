package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.Client;
import org.springframework.data.domain.Page;

public interface ICustomerService {
    Client checkClient(String email);
    Client checkClient(Long code);
    void deleteClient(Long code);
    Client saveClient(Client c);
    Client updateClient(Client c);
    Page<Client> clientsPaginated(int page, int size);
}
