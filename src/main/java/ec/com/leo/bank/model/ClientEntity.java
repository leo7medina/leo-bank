package ec.com.leo.bank.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CLIENTE")
public class ClientEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCLIENTE")
    private Integer idClient;

    @Column(name = "PASSWORD", length = 45)
    private String password;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "IDPERSONA")
    private Integer idPerson;

    @OneToOne
    @JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA", insertable = false, updatable = false)
    @JsonManagedReference
    private PersonEntity personEntity;

    @OneToMany(mappedBy = "clientEntity")
    @JsonManagedReference
    private List<AccountEntity> accountCol;
}
