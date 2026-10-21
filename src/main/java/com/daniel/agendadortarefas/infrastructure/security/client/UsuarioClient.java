package com.daniel.agendadortarefas.infrastructure.security.client;


import com.daniel.agendadortarefas.business.dto.response.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario" , url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping("/usuario")
    UsuarioResponseDTO buscarUsuarioPorEmail(@RequestParam("email") String email, @RequestHeader("Authorization") String token);
}
