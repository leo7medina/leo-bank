package ec.com.leo.bank;

import ec.com.leo.bank.model.PersonEntity;
import ec.com.leo.bank.vo.ClientVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class GeneralUtil {


    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    /**
     * Constructor.
     */
    private GeneralUtil() {}


    public static <D> D convert(Object origin, Class<D> element) {
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        if (Objects.nonNull(origin)) {
            return MODEL_MAPPER.map(origin, element);
        }
        return null;
    }

    public static <D> List<D> convertList(List<?> listData, Class<D> element) {
        if (listData.isEmpty()) {
            return new ArrayList<>();
        } else {
            return listData.stream().map(item -> convert(item, element)).collect(Collectors.toList());
        }
    }

    public static PersonEntity getPersonEntityOfClient(ClientVO clientVO) {
        if (Objects.nonNull(clientVO.getIdPerson()) && Objects.nonNull(clientVO.getIdentification())) {
            PersonEntity personEntity = new PersonEntity();
            personEntity.setIdPerson(clientVO.getIdPerson());
            personEntity.setAge(clientVO.getAge());
            personEntity.setGender(clientVO.getGender());
            personEntity.setAddress(clientVO.getAddress());
            personEntity.setPhone(clientVO.getPhone());
            personEntity.setName(clientVO.getName());
            personEntity.setIdentification(clientVO.getIdentification());
            return personEntity;
        }
        return null;
    }
}
