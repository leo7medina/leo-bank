package ec.com.leo.bank.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * PersonVO.
 * @author leonardo
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class PersonVO {
    private Integer idPerson;
    @NotNull
    private String name;
    @NotNull
    private String gender;
    private Integer age;
    @NotNull
    private String identification;
    private String address;
    private String phone;
}

