package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.converters;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import org.springframework.core.convert.converter.Converter;

public class StringToEntityStatusEnumConverter implements Converter<String, EntityStatus> {

    @Override
    public EntityStatus convert(String source) {
        return EntityStatus.valueOf(source.toUpperCase());
    }
}
