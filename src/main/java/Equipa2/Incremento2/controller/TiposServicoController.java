package Equipa2.Incremento2.controller;

import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.TiposServico;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.model.dto.TiposServicoDTO;
import Equipa2.Incremento2.service.ServicoService;
import Equipa2.Incremento2.service.TiposServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/tiposServico")
@CrossOrigin("*")
public class TiposServicoController {

    @Autowired
    private TiposServicoService tiposServicoService;

    @Autowired
    private ServicoService servicoService;

    @PostMapping
    public ResponseEntity<TiposServicoDTO> createTipoServico(@RequestBody TiposServicoDTO tiposServicoDTO){
        TiposServico tiposServico = tiposServicoService.createTipoServico(tiposServicoDTO);

        tiposServicoDTO.setId(tiposServico.getId());
        tiposServicoDTO.setDataCreated(tiposServico.getDataCreated());
        tiposServicoDTO.setStatus(tiposServico.getStatus());

        ArrayList<ServicoDTO> servicosDTO = new ArrayList<>();
        for(Servico ser : tiposServico.getServicos()){
            ServicoDTO serDTO = servicoService.findDTOById(ser.getId());
            servicosDTO.add(serDTO);
        }

        tiposServicoDTO.setServicos(servicosDTO);

        return ResponseEntity.ok().body(tiposServicoDTO);
    }

}
