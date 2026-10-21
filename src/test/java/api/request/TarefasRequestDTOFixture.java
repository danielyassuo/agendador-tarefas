package api.request;

import com.daniel.agendadortarefas.business.dto.request.TarefasRequestDTO;
import com.daniel.agendadortarefas.infrastructure.enums.Status;

import java.time.LocalDateTime;

public class TarefasRequestDTOFixture {

    public static TarefasRequestDTO build (String id, String nomeTarefa, String descricao, LocalDateTime dataCriacao, LocalDateTime dataEvento, String emailUsuario, LocalDateTime dataAlteracao, Status status){
        return new TarefasRequestDTO(id, nomeTarefa, descricao, dataCriacao, dataEvento, emailUsuario, dataAlteracao, status);
    }
}
