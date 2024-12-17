package Equipa2.Incremento2.service;

import Equipa2.Incremento2.model.Admin;
import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.TiposServico;
import Equipa2.Incremento2.model.dto.TiposServicoDTO;
import Equipa2.Incremento2.model.enums.StatusAtivo;
import Equipa2.Incremento2.repository.TiposServicoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TiposServicoService {

    @Autowired
    private TiposServicoRespository tiposServicoRespository;

    @Autowired
    private UtilizadorService utilizadorService;

    public TiposServico createTipoServico(@RequestBody TiposServicoDTO tiposServicoDTO){

        TiposServico tipo = new TiposServico();
        tipo.setNome(tiposServicoDTO.getNome());
        tipo.setAdmin((Admin) utilizadorService.findById(tiposServicoDTO.getAdminId()));

        return tiposServicoRespository.save(tipo);
    }
}
