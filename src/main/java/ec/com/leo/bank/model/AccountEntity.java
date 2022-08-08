package ec.com.leo.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity(name = "CUENTA")
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCUENTA")
    private Integer idAccount;

    @NotNull(message = "Numero de cuenta no puede ser nulo")
    @Column(name = "NUMERO", length = 20, unique = true)
    private String number;

    @NotNull
    @Column(name = "TIPOCUENTA", length = 45)
    private String typeAccount;

    @NotNull
    @Column(name = "SALDOINICIAL")
    private BigDecimal balanceInitial;

    @NotNull
    @Column(name = "ESTADO")
    private Boolean status;

    @NotNull
    @Column(name = "IDCLIENTE", nullable = false)
    private Integer idClient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCLIENTE", referencedColumnName = "IDCLIENTE", insertable = false, updatable = false)
    @JsonBackReference
    private ClientEntity clientEntity;

    @OneToMany(mappedBy = "accountEntity")
    @JsonManagedReference
    private List<MovementEntity> movementsCol;

}
