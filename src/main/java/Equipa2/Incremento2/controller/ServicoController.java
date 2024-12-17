package Equipa2.Incremento2.controller;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Equipa2.Incremento2.service.TiposServicoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.service.ServicoService;
import Equipa2.Incremento2.service.UtilizadorService;

/**
 * Controlador para operações relacionadas com a entidade Servico.
 */
@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "*")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private TiposServicoService tiposServicoService;

    // @GetMapping
    // public List<ServicoDTO> getAllServicos() {
    //     ListzservicoService.findAll();
    //     return
    // }

    /**
     * Encontra um serviço pelo ID.
     * @param id
     * @return o serviço com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> getServicoById(@PathVariable UUID id) {
        ServicoDTO servico = servicoService.findDTOById(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(servico);
    }

//     /**
//     * Encontra todas os serviços de um tipo.
//     *
//     * @param tipo o tipo do serviço
//     * @return uma lista de todos os serviços com esse tipo
//     */
//    @GetMapping("/tipo")
//    public ResponseEntity<List<ServicoDTO>> getAllServicosByTipo(@RequestParam(value="tipo") Servicos tipo){
//        List<Servico> servicos = servicoService.findAllByTipo(tipo.name());
//
//        List<ServicoDTO> dtos = new ArrayList<>();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
//
//        for (Servico ser : servicos) {
//            String formattedDate = ser.getData().format(dtf);
//            dtos.add(new ServicoDTO(
//                    ser.getId(),
//                    ser.getTipo(),
//                    ser.getDescricao(),
//                    formattedDate,
//                    ser.getValorHora(),
//                    utilizadorService.findDTOById(ser.getProfissional().getId())
//            ));
//        }
//        return ResponseEntity.ok().body(dtos);
//    }

    

    /**
     * Encontra todos os serviços.
     * @return uma lista de todos os serviços
     */
    @GetMapping
    public ResponseEntity<List<ServicoDTO>> getAll() {
        List<Servico> servicos = servicoService.findAll();

        List<ServicoDTO> dtos = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


        for (Servico ser : servicos) {
            String formattedDate = ser.getData().format(dtf);
            dtos.add(new ServicoDTO(
                    ser.getId(),
                    tiposServicoService.findDTOById(ser.getTipoServico().getId()),
                    ser.getDescricao(),
                    formattedDate,
                    ser.getValorHora(),
                    utilizadorService.findDTOById(ser.getProfissional().getId())
            ));
        }

        return ResponseEntity.ok().body(dtos);
    }

    /**
     * Encontra todos os serviços de um profissional.
     * @param profissionalId
     * @return uma lista de todos os serviços de um profissional
     */
    @GetMapping("/profissional/{profissionalId}")
    public ResponseEntity<List<ServicoDTO>> getAllByProfissionalId(@PathVariable UUID profissionalId) {
        List<Servico> servicos = servicoService.findAllByProfissionalId(profissionalId);

        List<ServicoDTO> servicoDTOs = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for (Servico ser : servicos) {
            String formattedDate = ser.getData().format(dtf);
            servicoDTOs.add(new ServicoDTO(
                    ser.getId(),
                    tiposServicoService.findDTOById(ser.getTipoServico().getId()),
                    ser.getDescricao(),
                    formattedDate,
                    ser.getValorHora(),
                    utilizadorService.findDTOById(ser.getProfissional().getId())
            ));
        }

        return ResponseEntity.ok(servicoDTOs);
    }

    /**
     * Cria um serviço.
     * @param servico
     * @return o serviço criado
     */
    @PostMapping
    public ResponseEntity<ServicoDTO> createServico(@RequestBody ServicoDTO servico) {
        Profissional pro = (Profissional) utilizadorService.findById(servico.getProfissional().getId());

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Servico ser = new Servico();
        ser.setTipoServico(tiposServicoService.getTiposServicoByNome(servico.getTipo().getNome()));
        ser.setDescricao(servico.getDescricao());
        ser.setData(date);
        ser.setValorHora(servico.getValorHora());
        ser.setProfissional(pro);

        servicoService.save(ser);

        servico.setId(ser.getId());
        servico.setData(date.format(dtf));
        servico.setProfissional(utilizadorService.findDTOById(pro.getId()));

        return ResponseEntity.ok().body(servico);
    }

    /**
     * Atualiza um serviço.
     * @param id
     * @param servicoDetails
     * @return o serviço atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<Servico> updateServico(@PathVariable UUID id, @RequestBody Servico servicoDetails) {
        Servico servico = servicoService.findById(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        servico.setTipoServico(servicoDetails.getTipoServico());
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setData(servicoDetails.getData());
        servico.setValorHora(servicoDetails.getValorHora());
        Servico updatedServico = servicoService.save(servico);

        return ResponseEntity.ok(updatedServico);
    }

    /**
     * Apaga um serviço.
     * @param id
     * @return resposta de sucesso ou erro
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServico(@PathVariable UUID id) {
        Servico servico = servicoService.findById(id);

        if (servico == null) {
            return ResponseEntity.notFound().build();
        }

        servicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
