package bz.faycal.smallbank.service;

import bz.faycal.smallbank.entities.Client;

public interface ICustomerService {
    Client checkClient(String email);
    Client checkClient(Long code);
    void deleteClient(Long code);
    Client saveClient(Client c);
    Client updateClient(Client c);
}
