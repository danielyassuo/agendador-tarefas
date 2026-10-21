package com.daniel.agendadortarefas.controller;

import com.daniel.agendadortarefas.business.TarefasService;
import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.infrastructure.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {
    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasResponseDTO> gravarTarefas(@RequestBody TarefasRequestDTO dto, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.gravarTarefas(token ,dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasResponseDTO>> buscaListaDeTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefasService.buscaTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasResponseDTO>> buscaTarefaPorEmail (@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefasService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id){
        tarefasService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasResponseDTO> alteraStatusNotificacao(@RequestParam("status") Status status,
                                                              @RequestParam("id") String id){
        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasResponseDTO> updateTarefas (@RequestBody TarefasRequestDTO dto, @RequestParam String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto, id));
    }




}
