package Equipa2.Incremento2.controller;

import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.TiposServico;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.model.dto.TiposServicoDTO;
import Equipa2.Incremento2.model.enums.StatusAtivo;
import Equipa2.Incremento2.service.ServicoService;
import Equipa2.Incremento2.service.TiposServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tiposServico")
@CrossOrigin("*")
public class TiposServicoController {

    @Autowired
    private TiposServicoService tiposServicoService;

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<?> createTipoServico(@RequestBody TiposServicoDTO tiposServicoDTO){

        TiposServico tiposServico = tiposServicoService.getTiposServicoByNome(tiposServicoDTO.getNome());

        if(tiposServico != null){
            if(tiposServico.getStatus() == StatusAtivo.ATIVO){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um Tipo de serviço com o nome '" + tiposServicoDTO.getNome() + "'");
            } else{
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Já existe um Tipo de serviço com o nome '" + tiposServicoDTO.getNome() + "', mas está inativo");
            }
        }

        tiposServico = tiposServicoService.createTipoServico(tiposServicoDTO);

        tiposServicoDTO.setId(tiposServico.getId());
        tiposServicoDTO.setDataCreated(tiposServico.getDataCreated());
        tiposServicoDTO.setStatus(tiposServico.getStatus());

        ArrayList<ServicoDTO> servicosDTO = new ArrayList<>();
        for(Servico ser : tiposServico.getServicos()){
            ServicoDTO serDTO = servicoService.findDTOById(ser.getId());
            servicosDTO.add(serDTO);
        }


        return ResponseEntity.ok().body(tiposServicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TiposServicoDTO>> getAllTiposServico(){
        List<TiposServico> tiposServico = tiposServicoService.getAllTiposServico();

        ArrayList<TiposServicoDTO> tiposServicosDTO = new ArrayList<>();
        for(TiposServico tipSer : tiposServico){
            TiposServicoDTO tipSerDTO = tiposServicoService.findDTOById(tipSer.getId());
            tiposServicosDTO.add(tipSerDTO);
        }

        return ResponseEntity.ok().body(tiposServicosDTO);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<TiposServicoDTO>> getAllTiposServicoAtivos(){
        List<TiposServico> tiposServico = tiposServicoService.getAllTiposServicoAtivos();

        ArrayList<TiposServicoDTO> tiposServicosDTO = new ArrayList<>();

        for(TiposServico tipSer : tiposServico){
            TiposServicoDTO tipSerDTO = tiposServicoService.findDTOById(tipSer.getId());
            tiposServicosDTO.add(tipSerDTO);
        }

        return ResponseEntity.ok().body(tiposServicosDTO);
    }

}
