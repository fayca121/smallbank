package bz.faycal.smallbank.repository;

import bz.faycal.smallbank.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperationRepository extends JpaRepository<Operation,Long> {
}
