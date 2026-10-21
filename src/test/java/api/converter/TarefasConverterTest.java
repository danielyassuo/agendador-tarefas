package api.converter;


import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.business.mapper.TarefasConverter;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.daniel.agendadortarefas.infrastructure.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TarefasConverterTest {

    TarefasConverter converter;

    TarefasEntity entity;

    TarefasRequestDTO tarefasRequestDTO;

    TarefasResponseDTO tarefasResponseDTO;

    List<TarefasResponseDTO> listaDTO;

    List<TarefasEntity> listaEntity;

    List<TarefasRequestDTO> listaDTORequest;

    @BeforeEach
    public void setup () {
        converter = Mappers.getMapper(TarefasConverter.class);

        entity = TarefasEntity.builder()
                .id("1")
                .nomeTarefa("Fazer testes unitários")
                .descricao("Terminar de realizar os testes unitários")
                .dataCriacao(LocalDateTime.of(2026, 10, 20, 10, 51))
                .dataEvento(LocalDateTime.of(2026, 10, 21, 14, 30 ))
                .emailUsuario("usuario@gmail.com")
                .dataAlteracao(LocalDateTime.of(2026, 10, 21, 12, 20))
                .status(Status.PENDENTE)
                .build();


        tarefasRequestDTO = TarefasRequestDTO.builder()
                .id("1")
                .nomeTarefa("Fazer testes unitários")
                .descricao("Terminar de realizar os testes unitários")
                .dataCriacao(LocalDateTime.of(2026, 10, 20, 10, 51))
                .dataEvento(LocalDateTime.of(2026, 10, 21, 14, 30 ))
                .emailUsuario("usuario@gmail.com")
                .dataAlteracao(LocalDateTime.of(2026, 10, 21, 12, 20))
                .status(Status.PENDENTE)
                .build();


        tarefasResponseDTO = TarefasResponseDTO.builder()
                .id("1")
                .nomeTarefa("Fazer testes unitários")
                .descricao("Terminar de realizar os testes unitários")
                .dataCriacao(LocalDateTime.of(2026, 10, 20, 10, 51))
                .dataEvento(LocalDateTime.of(2026, 10, 21, 14, 30 ))
                .emailUsuario("usuario@gmail.com")
                .dataAlteracao(LocalDateTime.of(2026, 10, 21, 12, 20))
                .status(Status.PENDENTE)
                .build();

        listaDTO = List.of(tarefasResponseDTO);
        listaDTORequest = List.of(tarefasRequestDTO);
        listaEntity = List.of(entity);
    }

    @Test
    void deveConverterParaDTO () {
        TarefasResponseDTO dto = converter.paraTarefaDTO(entity);

        assertEquals(tarefasResponseDTO, dto);
    }

    @Test
    void deveConverterParaEntity () {
        TarefasEntity entidade = converter.paraTarefaEntity(tarefasRequestDTO);

        assertEquals(entity, entidade);
    }

    @Test
    void deveConverterParaListaDTO () {
        List<TarefasResponseDTO> listaDeDTO = converter.paraListaTarefasDTO(listaEntity);

        assertEquals(listaDTO, listaDeDTO);
    }

    @Test
    void deveConverterParaListaEntity () {
        List<TarefasEntity> listaDeEntity = converter.paraListaTarefasEntity(listaDTORequest);

        assertEquals(listaEntity, listaDeEntity);
    }


}
