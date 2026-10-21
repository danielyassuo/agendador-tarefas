package api.response;

import com.daniel.agendadortarefas.business.dto.request.UsuarioRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.UsuarioResponseDTO;

public class UsuarioResponseDTOFixture {

    public static UsuarioResponseDTO build (String email, String senha){
        return new UsuarioResponseDTO(email, senha);
    }
}
