package com.banquito.core.banking.creditos.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.TablaAmortizacion;
import com.banquito.core.banking.creditos.dto.TablaAmortizacionDTO;

@Mapper(componentModel = "spring")
public interface TablaAmortizacionMapper {

    TablaAmortizacionMapper INSTANCE = Mappers.getMapper(TablaAmortizacionMapper.class);

    TablaAmortizacionDTO DTOToEntity(TablaAmortizacion entity);

    @Mappings({
            @Mapping(target = "fechaUltimoCambio", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    TablaAmortizacion entityToDTO(TablaAmortizacionDTO dto);
}