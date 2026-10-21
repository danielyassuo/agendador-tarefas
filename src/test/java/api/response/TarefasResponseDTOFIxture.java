package api.response;

import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.business.dto.response.TarefasResponseDTO;
import com.daniel.agendadortarefas.infrastructure.enums.Status;

import java.time.LocalDateTime;

public class TarefasResponseDTOFIxture {
    public static TarefasResponseDTO build (String id, String nomeTarefa, String descricao, LocalDateTime dataCriacao, LocalDateTime dataEvento, String emailUsuario, LocalDateTime dataAlteracao, Status status){
        return new TarefasResponseDTO(id, nomeTarefa, descricao, dataCriacao, dataEvento, emailUsuario, dataAlteracao, status);
    }
}
