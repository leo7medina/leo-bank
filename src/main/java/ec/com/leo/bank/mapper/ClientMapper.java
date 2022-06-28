package ec.com.leo.bank.mapper;

import ec.com.leo.bank.model.ClientEntity;
import ec.com.leo.bank.vo.ClientVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "idClient", target = "idClient"),
            @Mapping(source = "status", target = "status"),
            @Mapping(source = "idPerson", target = "idPerson"),
            @Mapping(source = "personEntity.name", target = "name"),
            @Mapping(source = "personEntity.gender", target = "gender"),
            @Mapping(source = "personEntity.age", target = "age"),
            @Mapping(source = "personEntity.identification", target = "identification"),
            @Mapping(source = "personEntity.address", target = "address"),
            @Mapping(source = "personEntity.phone", target = "phone")

    })
    ClientVO toClientVO(ClientEntity clientEntity);


    List<ClientVO> toClientVOs(List<ClientEntity> clientEntityList);


    @InheritInverseConfiguration
    ClientEntity toClientEntity(ClientVO clientVO);

}
