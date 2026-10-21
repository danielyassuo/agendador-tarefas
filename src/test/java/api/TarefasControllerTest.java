package api;


import com.daniel.agendadortarefas.business.TarefasService;
import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.daniel.agendadortarefas.business.mapper.TarefasConverter;
import com.daniel.agendadortarefas.controller.TarefasController;
import com.daniel.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.daniel.agendadortarefas.infrastructure.enums.Status;
import com.daniel.agendadortarefas.infrastructure.exceptions.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TarefasControllerTest {

    @InjectMocks
    TarefasController controller;

    @Mock
    TarefasService service;

    @Mock
    TarefasConverter converter;

    private final ObjectMapper objectMapper =new ObjectMapper();

    private MockMvc mockMvc;

    @Mock
    TarefaUpdateConverter updateConverter;

    TarefasEntity entity;

    TarefasRequestDTO tarefasRequestDTO;

    TarefasResponseDTO tarefasResponseDTO;

    List<TarefasResponseDTO> listaDTO;

    List<TarefasEntity> listaEntity;

    List<TarefasRequestDTO> listaDTORequest;

    private String url;

    private String json;

    @BeforeEach
    public void setup () {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).alwaysDo(print()).build();
        url = "/tarefas";
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

        json = objectMapper.writeValueAsString(tarefasResponseDTO);
    }

    @Test
    void deveGravarTarefa () throws Exception {
        String token = "Bearer tokenGerado";
        when(service.gravarTarefas(token, tarefasRequestDTO)).thenReturn(tarefasResponseDTO);

        mockMvc.perform(post(url)
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(status().isOk());

        verify(service).gravarTarefas(token, tarefasRequestDTO);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveBuscarListaDeTarefasPorPeriodo () throws Exception {
        LocalDateTime dataInicial = LocalDateTime.of(2026, 10, 27, 15, 30);
        LocalDateTime dataFinal   = LocalDateTime.of(2026, 10, 30, 14, 29);
        when(service.buscaTarefasPorPeriodo(dataInicial, dataFinal)).thenReturn(listaDTO);

        mockMvc.perform(get(url+"/eventos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("dataInicial", dataInicial.toString())
                .param("dataFinal", dataFinal.toString())
        ).andExpect(status().isOk());

        verify(service).buscaTarefasPorPeriodo(dataInicial, dataFinal);
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveBuscarTarefasPorEmail () throws Exception {
        when(service.buscaTarefasPorEmail("Bearer tokenGerado")).thenReturn(listaDTO);

        mockMvc.perform(get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer tokenGerado")
        ).andExpect(status().isOk());

        verify(service).buscaTarefasPorEmail("Bearer tokenGerado");
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveDeletarTarefaPorId () throws Exception {
        doNothing().when(service).deletaTarefaPorId(tarefasRequestDTO.getId());

        mockMvc.perform(delete(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("id", String.valueOf(tarefasRequestDTO.getId()))
        ).andExpect(status().isOk());

        verify(service).deletaTarefaPorId(tarefasRequestDTO.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveAlterarStatusNotificacao () throws Exception {
        when(service.alteraStatus(tarefasRequestDTO.getStatus(), tarefasRequestDTO.getId())).thenReturn(tarefasResponseDTO);

        mockMvc.perform(patch(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .param("status", String.valueOf(tarefasRequestDTO.getStatus()))
                .param("id", String.valueOf(tarefasRequestDTO.getId()))
        ).andExpect(status().isOk());

        verify(service).alteraStatus(tarefasRequestDTO.getStatus(), tarefasRequestDTO.getId());
        verifyNoMoreInteractions(service);
    }

    @Test
    void deveAtualizarTarefa () throws Exception {
        when(service.updateTarefas(tarefasRequestDTO, tarefasRequestDTO.getId())).thenReturn(tarefasResponseDTO);

        mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .param("id", String.valueOf(tarefasRequestDTO.getId()))
        ).andExpect(status().isOk());

        verify(service).updateTarefas(tarefasRequestDTO, tarefasRequestDTO.getId());
        verifyNoMoreInteractions(service);
    }


}
