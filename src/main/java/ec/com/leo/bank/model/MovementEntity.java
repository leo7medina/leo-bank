package ec.com.leo.bank.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity(name = "MOVIMIENTO")
public class MovementEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDMOVIMIENTO")
    private Integer idMovement;

    @Column(name = "FECHA")
    private Date date;

    @Column(name = "TIPOMOVIMIENTO", length = 30)
    private String typeMovement;

    @Column(name = "VALOR")
    private Double value;

    @Column(name = "SALDO")
    private Double balance;

    @Column(name = "IDCUENTA")
    private Integer idAccount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCUENTA", referencedColumnName = "IDCUENTA", insertable = false, updatable = false)
    @JsonBackReference
    private AccountEntity accountEntity;
}
