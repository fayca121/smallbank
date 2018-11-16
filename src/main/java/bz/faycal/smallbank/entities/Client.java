package bz.faycal.smallbank.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue
    private Long code;
    private String name;
    private String email;
    @OneToMany(mappedBy = "client",fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
