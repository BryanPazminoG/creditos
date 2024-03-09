package com.banquito.core.banking.creditos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.TasaInteres;
import com.banquito.core.banking.creditos.dto.TasaInteresDTO;

@Mapper(componentModel = "spring")
public interface TasaInteresMapper {

    TasaInteresMapper INSTANCE = Mappers.getMapper(TasaInteresMapper.class);

    TasaInteres entityToDTO(TasaInteresDTO dto);
    @Mappings({
            @Mapping(target = "fechaUltimoCambio", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    TasaInteresDTO DTOToEntity(TasaInteres entity);
}