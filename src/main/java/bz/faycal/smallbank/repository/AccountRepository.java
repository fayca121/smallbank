package bz.faycal.smallbank.repository;

import bz.faycal.smallbank.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account,String> {
}
