package business;


import com.daniel.agendadortarefas.business.TarefasService;
import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.daniel.agendadortarefas.business.mapper.TarefasConverter;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.daniel.agendadortarefas.infrastructure.enums.Status;
import com.daniel.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.daniel.agendadortarefas.infrastructure.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TarefasServiceTest {

    @InjectMocks
    TarefasService service;

    @Mock
    TarefasRepository repository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    Clock clock;


    @Mock
    TarefasConverter converter;

    @Mock
    TarefaUpdateConverter updateConverter;

    TarefasEntity entity;

    TarefasRequestDTO tarefasRequestDTO;

    TarefasResponseDTO tarefasResponseDTO;

    List<TarefasResponseDTO> listaDTO;

    List<TarefasEntity> listaEntity;

    List<TarefasRequestDTO> listaDTORequest;

    @BeforeEach
    public void setup () {
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
    void deveSalvarTarefa () {
        String token = "Bearer tokenGerado";

        when(jwtUtil.extraitEmailToken(token.substring(7))).thenReturn(tarefasRequestDTO.getEmailUsuario());
        when(converter.paraTarefaEntity(tarefasRequestDTO)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(converter.paraTarefaDTO(entity)).thenReturn(tarefasResponseDTO);

        TarefasResponseDTO dto = service.gravarTarefas(token, tarefasRequestDTO);


        assertEquals(tarefasResponseDTO, dto);
    }

    @Test
    void deveBuscarTarefasPorPeriodo () {
        when(repository.findByDataEventoBetweenAndStatus(LocalDateTime.of(2026, 10, 27, 15, 30), LocalDateTime.of(2026, 10, 30, 14, 29), Status.PENDENTE )).thenReturn(listaEntity);
        when(converter.paraListaTarefasDTO(listaEntity)).thenReturn(listaDTO);

        List<TarefasResponseDTO> listaResultado = service.buscaTarefasPorPeriodo(LocalDateTime.of(2026, 10, 27, 15, 30), LocalDateTime.of(2026, 10, 30, 14, 29));

        assertEquals(listaDTO, listaResultado);
    }

    @Test
    void deveBuscarTarefaPorEmail () {
        String token = "Bearer tokenGerado";
        when(jwtUtil.extraitEmailToken(token.substring(7))).thenReturn(tarefasRequestDTO.getEmailUsuario());
        when(repository.findByEmailUsuario(tarefasRequestDTO.getEmailUsuario())).thenReturn(listaEntity);
        when(converter.paraListaTarefasDTO(listaEntity)).thenReturn(listaDTO);

        List<TarefasResponseDTO> listaResultado = service.buscaTarefasPorEmail(token);

        assertEquals(listaDTO, listaResultado);
    }

    @Test
    void deveDeletarTarefaPorId () {
        when(repository.existsById(tarefasRequestDTO.getId())).thenReturn(true);

        service.deletaTarefaPorId(tarefasRequestDTO.getId());
        verify(repository).deleteById(tarefasRequestDTO.getId());
    }

    @Test
    void deveAlterarStatus () {
        when(repository.findById(tarefasRequestDTO.getId())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(converter.paraTarefaDTO(entity)).thenReturn(tarefasResponseDTO);

        TarefasResponseDTO dtoResultado = service.alteraStatus(tarefasRequestDTO.getStatus(), tarefasRequestDTO.getId());

        assertEquals(tarefasResponseDTO, dtoResultado);
        assertEquals(tarefasResponseDTO.getStatus(), dtoResultado.getStatus());
    }

    @Test
    void deveAtualizarTarefas () {
        when(repository.findById(tarefasRequestDTO.getId())).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(entity);
        when(converter.paraTarefaDTO(entity)).thenReturn(tarefasResponseDTO);

        TarefasResponseDTO resultado = service.updateTarefas(tarefasRequestDTO, tarefasRequestDTO.getId());

        assertEquals(tarefasResponseDTO, resultado);
        verify(updateConverter).updateTarefas(tarefasRequestDTO, entity);
    }

}
