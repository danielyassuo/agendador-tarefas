package com.daniel.agendadortarefas.business.mapper;


import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {
    TarefasEntity paraTarefaEntity(TarefasRequestDTO tarefasDTO);

    TarefasResponseDTO paraTarefaDTO(TarefasEntity tarefasEntity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasRequestDTO> tarefasDTOS);

    List<TarefasResponseDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntity);


}
