package com.daniel.agendadortarefas.business.mapper;

import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-20T10:21:33-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.19 (Azul Systems, Inc.)"
)
@Component
public class TarefaUpdateConverterImpl implements TarefaUpdateConverter {

    @Override
    public void updateTarefas(TarefasRequestDTO dto, TarefasEntity entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getNomeTarefa() != null ) {
            entity.setNomeTarefa( dto.getNomeTarefa() );
        }
        if ( dto.getDescricao() != null ) {
            entity.setDescricao( dto.getDescricao() );
        }
        if ( dto.getDataCriacao() != null ) {
            entity.setDataCriacao( dto.getDataCriacao() );
        }
        if ( dto.getDataEvento() != null ) {
            entity.setDataEvento( dto.getDataEvento() );
        }
        if ( dto.getEmailUsuario() != null ) {
            entity.setEmailUsuario( dto.getEmailUsuario() );
        }
        if ( dto.getDataAlteracao() != null ) {
            entity.setDataAlteracao( dto.getDataAlteracao() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
    }
}
