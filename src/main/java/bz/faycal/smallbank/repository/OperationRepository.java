package bz.faycal.smallbank.repository;

import bz.faycal.smallbank.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    @Query("select o from Operation o where o.account.accountCode=:x order by o.operationDate desc")
    Page<Operation> findAllOperationByAccount(@Param("x") String accountCode, Pageable pageable);
}

