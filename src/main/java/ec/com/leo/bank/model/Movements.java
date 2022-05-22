package ec.com.leo.bank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity(name = "MOVIMIENTO")
public class Movements implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDMOVIMIENTO")
    private Integer idMovement;

    @Column(name = "FECHA")
    private Date date;

    @Column(name = "TIPOMOVIMIENTO", length = 45)
    private String typeMovement;

    @Column(name = "VALOR")
    private Double value;

    @Column(name = "SALDO")
    private Double balance;

    @Column(name = "IDCUENTA")
    private Integer idAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCUENTA", referencedColumnName = "IDCUENTA", insertable = false, updatable = false)
    private Account accountEntity;
}
