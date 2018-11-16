package bz.faycal.smallbank.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OPS_TYPE",discriminatorType = DiscriminatorType.STRING,length = 1)
public abstract class Operation implements Serializable {
    @Id
    @GeneratedValue
    private Long opsNumber;
    private Date operationDate;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "account_code")
    private Account account;

    public Operation(Date operationDate, double amount, Account account) {
        this.operationDate = operationDate;
        this.amount = amount;
        this.account = account;
    }
}
