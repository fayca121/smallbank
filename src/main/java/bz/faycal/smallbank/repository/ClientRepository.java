package bz.faycal.smallbank.repository;

import bz.faycal.smallbank.entities.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client,Long> {
}
