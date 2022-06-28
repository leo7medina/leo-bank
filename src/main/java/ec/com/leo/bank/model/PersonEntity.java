package ec.com.leo.bank.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity(name = "PERSONA")
public class PersonEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDPERSONA")
    private Integer idPerson;

    @Column(name = "NOMBRE", length = 60)
    private String name;

    @Column(name = "GENERO", length = 20)
    private String gender;

    @Column(name = "EDAD")
    private Integer age;

    @Column(name = "IDENTIFICACION", length = 13)
    private String identification;

    @Column(name = "DIRECCION", length = 200)
    private String address;

    @Column(name = "TELEFONO", length = 10)
    private String phone;

    @OneToOne(mappedBy = "personEntity", fetch = FetchType.LAZY)
    @JsonBackReference
    //@JoinColumn(name = "IDPERSONA", referencedColumnName = "IDPERSONA", insertable = false, updatable = false)
    private ClientEntity client;
}
