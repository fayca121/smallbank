package bz.faycal.smallbank.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE",discriminatorType = DiscriminatorType.STRING,length = 2)
public abstract class Account implements Serializable {
    @Id
    private String accountCode;
    private Date creationDate;
    private double balance;
    @ManyToOne
    @JoinColumn(name = "client_code")
    private Client client;
    @OneToMany(mappedBy = "account")
    private List<Operation> operations;

    public Account(String accountCode, Date creationDate, double balance, Client client) {
        this.accountCode = accountCode;
        this.creationDate = creationDate;
        this.balance = balance;
        this.client = client;
    }
}
