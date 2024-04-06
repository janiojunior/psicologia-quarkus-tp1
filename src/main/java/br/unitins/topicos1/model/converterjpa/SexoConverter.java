package br.unitins.topicos1.model.converterjpa;

import br.unitins.topicos1.model.Sexo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SexoConverter implements AttributeConverter<Sexo, Integer>{

    @Override
    public Integer convertToDatabaseColumn(Sexo sexo) {
       return sexo.getId();
    }

    @Override
    public Sexo convertToEntityAttribute(Integer id) {
        return Sexo.valueOf(id);
    }

    
}