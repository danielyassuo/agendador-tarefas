package api.request;

import com.daniel.agendadortarefas.business.dto.request.UsuarioRequestDTO;

public class UsuarioRequestDTOFIxture {

    public static UsuarioRequestDTO build (String email, String senha){
        return new UsuarioRequestDTO(email, senha);
    }
}
