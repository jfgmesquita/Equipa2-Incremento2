package Equipa2.Incremento2.service;

import Equipa2.Incremento2.model.Admin;
import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.TiposServico;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.model.dto.TiposServicoDTO;
import Equipa2.Incremento2.model.enums.StatusAtivo;
import Equipa2.Incremento2.repository.TiposServicoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public TiposServicoDTO findDTOById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        TiposServico tipSer = tiposServicoRespository.findById(id).get();

        TiposServicoDTO tiposServicoDTO = new TiposServicoDTO();
        tiposServicoDTO.setId(tipSer.getId());
        tiposServicoDTO.setDataCreated(tipSer.getDataCreated());
        tiposServicoDTO.setNome(tipSer.getNome());
        tiposServicoDTO.setStatus(tipSer.getStatus());
        tiposServicoDTO.setAdminId(tipSer.getAdmin().getId());



        return tiposServicoDTO;
    }

    public List<TiposServico> getAllTiposServico(){
        return tiposServicoRespository.findAll();
    }

    public List<TiposServico> getAllTiposServicoAtivos(){
        List<TiposServico> tiposServicos = tiposServicoRespository.findAll();

        List<TiposServico> tiposServicoList = new ArrayList<>();

        for(TiposServico ts : tiposServicos){
            if(ts.getStatus() == StatusAtivo.ATIVO){
                tiposServicoList.add(ts);
            }
        }

        return tiposServicoList;
    }

    public TiposServico getTiposServicoByNome(String nome){
        return tiposServicoRespository.findByNome(nome);
    }
}
