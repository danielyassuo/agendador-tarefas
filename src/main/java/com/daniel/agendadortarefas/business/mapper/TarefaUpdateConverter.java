package com.daniel.agendadortarefas.business.mapper;


import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    void updateTarefas(TarefasRequestDTO dto, @MappingTarget TarefasEntity entity);
}
