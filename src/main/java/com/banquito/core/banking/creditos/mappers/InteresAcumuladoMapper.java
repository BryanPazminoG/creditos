package com.banquito.core.banking.creditos.mappers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.banquito.core.banking.creditos.domain.InteresAcumulado;
import com.banquito.core.banking.creditos.dto.InteresAcumuladoDTO;

@Mapper(componentModel = "spring")
public interface InteresAcumuladoMapper {
    
    InteresAcumuladoMapper INSTANCE = Mappers.getMapper( InteresAcumuladoMapper.class );

    InteresAcumuladoDTO DTOToEntity(InteresAcumulado entity);
    @Mappings({
        @Mapping(target = "fechaCreacion", ignore = true)
    })

    InteresAcumulado entityToDTO(InteresAcumuladoDTO dto);

}