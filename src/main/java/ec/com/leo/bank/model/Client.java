package ec.com.leo.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENTE")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDCLIENTE")
    private Integer idClient;

    @Column(name = "PASSWORD", length = 45)
    private String password;

    @Column(name = "ESTADO")
    private Boolean status;

    @Column(name = "IDPERSONA")
    private Integer idPerson;

    @OneToOne
    @JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA", insertable = false, updatable = false)
    private Person personEntity;

    @OneToMany(mappedBy = "clientEntity")
    private List<Account> accountCol;
}
