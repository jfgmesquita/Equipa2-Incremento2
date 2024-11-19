package Equipa2.Incremento2.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.dto.SolicitacaoDTO;
import Equipa2.Incremento2.service.UtilizadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Solicitacao;
import Equipa2.Incremento2.service.SolicitacaoService;

@RestController
@RequestMapping("/api/solicitacoes")
@CrossOrigin("*")
public class SolicitacaoController {
    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private UtilizadorService utilizadorService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> getAllSolicitacoes() {
        List<Solicitacao> solicitacoes = solicitacaoService.findAll();

        List<SolicitacaoDTO> dtos = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Solicitacao sol : solicitacoes){
            String formattedDate = dtf.format(sol.getData());

            dtos.add(
                    new SolicitacaoDTO(
                            sol.getId(),
                            sol.getStatus(),
                            utilizadorService.findDTOById(sol.getCliente().getId()),
                            utilizadorService.findDTOById(sol.getProfissional().getId()),
                            formattedDate
                    )
            );
        }

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> getSolicitacaoById(@PathVariable UUID id) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solicitacao);
    }

    @PostMapping
    public ResponseEntity<?> createSolicitacao(@RequestBody SolicitacaoDTO solicitacao) {

        Profissional pro = (Profissional) utilizadorService.findById(solicitacao.getProfissional().getId());
        Cliente cli = (Cliente) utilizadorService.findById(solicitacao.getCliente().getId());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime formattedDate;
        try {
            formattedDate = LocalDateTime.parse(solicitacao.getData(), dtf);
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Erro no formato da data. Use 'dd-MM-yyyy HH:mm'.");
        }

        Solicitacao sol = new Solicitacao();
        sol.setStatus(solicitacao.getStatus());
        sol.setCliente(cli);
        sol.setProfissional(pro);
        sol.setData(formattedDate);

        solicitacaoService.save(sol);

        solicitacao.setId(sol.getId());
        solicitacao.setCliente(utilizadorService.findDTOById(cli.getId()));
        solicitacao.setProfissional(utilizadorService.findDTOById(pro.getId()));

        return ResponseEntity.ok().body(solicitacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Solicitacao> updateSolicitacao(@PathVariable UUID id, @RequestBody Solicitacao solicitacaoDetails) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        solicitacao.setStatus(solicitacaoDetails.getStatus());
        solicitacao.setData(solicitacaoDetails.getData());
        Solicitacao updatedSolicitacao = solicitacaoService.save(solicitacao);

        return ResponseEntity.ok(updatedSolicitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitacao(@PathVariable UUID id) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        solicitacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
