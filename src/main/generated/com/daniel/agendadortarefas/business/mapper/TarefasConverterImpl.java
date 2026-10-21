package com.daniel.agendadortarefas.business.mapper;

import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-20T10:21:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Azul Systems, Inc.)"
)
@Component
public class TarefasConverterImpl implements TarefasConverter {

    @Override
    public TarefasEntity paraTarefaEntity(TarefasRequestDTO tarefasDTO) {
        if ( tarefasDTO == null ) {
            return null;
        }

        TarefasEntity.TarefasEntityBuilder tarefasEntity = TarefasEntity.builder();

        tarefasEntity.id( tarefasDTO.getId() );
        tarefasEntity.nomeTarefa( tarefasDTO.getNomeTarefa() );
        tarefasEntity.descricao( tarefasDTO.getDescricao() );
        tarefasEntity.dataCriacao( tarefasDTO.getDataCriacao() );
        tarefasEntity.dataEvento( tarefasDTO.getDataEvento() );
        tarefasEntity.emailUsuario( tarefasDTO.getEmailUsuario() );
        tarefasEntity.dataAlteracao( tarefasDTO.getDataAlteracao() );
        tarefasEntity.status( tarefasDTO.getStatus() );

        return tarefasEntity.build();
    }

    @Override
    public TarefasResponseDTO paraTarefaDTO(TarefasEntity tarefasEntity) {
        if ( tarefasEntity == null ) {
            return null;
        }

        TarefasResponseDTO.TarefasResponseDTOBuilder tarefasResponseDTO = TarefasResponseDTO.builder();

        tarefasResponseDTO.id( tarefasEntity.getId() );
        tarefasResponseDTO.nomeTarefa( tarefasEntity.getNomeTarefa() );
        tarefasResponseDTO.descricao( tarefasEntity.getDescricao() );
        tarefasResponseDTO.dataCriacao( tarefasEntity.getDataCriacao() );
        tarefasResponseDTO.dataEvento( tarefasEntity.getDataEvento() );
        tarefasResponseDTO.emailUsuario( tarefasEntity.getEmailUsuario() );
        tarefasResponseDTO.dataAlteracao( tarefasEntity.getDataAlteracao() );
        tarefasResponseDTO.status( tarefasEntity.getStatus() );

        return tarefasResponseDTO.build();
    }

    @Override
    public List<TarefasEntity> paraListaTarefasEntity(List<TarefasRequestDTO> tarefasDTOS) {
        if ( tarefasDTOS == null ) {
            return null;
        }

        List<TarefasEntity> list = new ArrayList<TarefasEntity>( tarefasDTOS.size() );
        for ( TarefasRequestDTO tarefasRequestDTO : tarefasDTOS ) {
            list.add( paraTarefaEntity( tarefasRequestDTO ) );
        }

        return list;
    }

    @Override
    public List<TarefasResponseDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntity) {
        if ( tarefasEntity == null ) {
            return null;
        }

        List<TarefasResponseDTO> list = new ArrayList<TarefasResponseDTO>( tarefasEntity.size() );
        for ( TarefasEntity tarefasEntity1 : tarefasEntity ) {
            list.add( paraTarefaDTO( tarefasEntity1 ) );
        }

        return list;
    }
}
