package ec.com.leo.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "CUENTA")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDCUENTA")
    private Integer idAccount;

    @Column(name = "NUMERO", length = 20)
    private String number;

    @Column(name = "TIPOCUENTA", length = 45)
    private String typeAccount;

    @Column(name = "SALDOINICIAL")
    private Double balanceInitial;

    @Column(name = "ESTADO")
    private Boolean status;

    @Column(name = "IDCLIENTE")
    private Integer idClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", insertable = false, updatable = false)
    private Client clientEntity;

    @OneToMany(mappedBy = "accountEntity")
    private List<Movements> movementsCol;

}
