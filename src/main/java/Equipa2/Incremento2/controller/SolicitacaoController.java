package Equipa2.Incremento2.controller;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.enums.UserType;
import Equipa2.Incremento2.model.Cliente;
import Equipa2.Incremento2.model.Profissional;
import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.Solicitacao;
import Equipa2.Incremento2.model.Utilizador;
import Equipa2.Incremento2.model.dto.SolicitacaoDTO;
import Equipa2.Incremento2.service.ServicoService;
import Equipa2.Incremento2.service.SolicitacaoService;
import Equipa2.Incremento2.service.UtilizadorService;

/**
 * Controlador para operações relacionadas com a entidade Solicitacao.
 */
@RestController
@RequestMapping("/api/solicitacoes")
@CrossOrigin("*")
public class SolicitacaoController {

    @Autowired
    private SolicitacaoService solicitacaoService;

    @Autowired
    private UtilizadorService utilizadorService;

    @Autowired
    private ServicoService servicoService;

    /**
     * Encontra todas as solicitações de um utilizador.
     *
     * @param utilizadorId o ID do utilizador
     * @return uma lista de todas as solicitações do utilizador
     */
    @GetMapping("/utilizador/{utilizadorId}")
    public ResponseEntity<?> getAllSolicitacoesByUtilizadorId(@PathVariable UUID utilizadorId){
        Utilizador uti = utilizadorService.findById(utilizadorId);

        List<Solicitacao> solicitacoes;

        if(uti.getUserType() == UserType.CLIENTE){
            Cliente cli = (Cliente) uti;
            solicitacoes = cli.getSolicitacoes();
        } else if(uti.getUserType() == UserType.PROFISSIONAL){
            Profissional pro = (Profissional) uti;
            solicitacoes = pro.getSolicitacoes();
        } else{
            return ResponseEntity.badRequest().body("Utilizadores do tipo ADMIN não tem solicitações");
        }

        List<SolicitacaoDTO> dtos = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Solicitacao sol : solicitacoes){
            String formattedDate = sol.getData().format(dtf);

            dtos.add(
                    new SolicitacaoDTO(
                            sol.getId(),
                            sol.getStatus(),
                            utilizadorService.findDTOById(sol.getCliente().getId()),
                            servicoService.findDTOById(sol.getServico().getId()),
                            formattedDate
                    )
            );
        }

        return ResponseEntity.ok().body(dtos);

    }

    /**
     * Encontra todas as solicitações.
     *
     * @return uma lista de todas as solicitações
     */
    @GetMapping
    public ResponseEntity<List<SolicitacaoDTO>> getAllSolicitacoes() {
        List<Solicitacao> solicitacoes = solicitacaoService.findAll();

        List<SolicitacaoDTO> dtos = new ArrayList<>();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        for(Solicitacao sol : solicitacoes){
            String formattedDate = sol.getData().format(dtf);

            dtos.add(
                    new SolicitacaoDTO(
                            sol.getId(),
                            sol.getStatus(),
                            utilizadorService.findDTOById(sol.getCliente().getId()),
                            servicoService.findDTOById(sol.getServico().getId()),
                            formattedDate
                    )
            );
        }

        return ResponseEntity.ok().body(dtos);
    }

    /**
     * Encontra uma solicitação pelo ID.
     *
     * @param id o ID da solicitação
     * @return a solicitação com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Solicitacao> getSolicitacaoById(@PathVariable UUID id) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(solicitacao);
    }

    /**
     * Cria uma solicitação.
     *
     * @param solicitacao a solicitação a ser criada
     * @return a solicitação criada
     */
    @PostMapping
    public ResponseEntity<?> createSolicitacao(@RequestBody SolicitacaoDTO solicitacao) {

        Servico ser = (Servico) servicoService.findById(solicitacao.getServico().getId());
        Profissional pro = (Profissional) utilizadorService.findById(ser.getProfissional().getId());
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
        sol.setServico(ser);
        sol.setData(formattedDate);

        solicitacaoService.save(sol);

        solicitacao.setId(sol.getId());
        solicitacao.setCliente(utilizadorService.findDTOById(cli.getId()));
        solicitacao.setServico(servicoService.findDTOById(ser.getId()));

        return ResponseEntity.ok().body(solicitacao);
    }

    /**
     * Atualiza uma solicitação.
     *
     * @param id o ID da solicitação
     * @param solicitacaoDetails os detalhes da solicitação a serem atualizados
     * @return a solicitação atualizada
     */
    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoDTO> updateStatusSolicitacao(@PathVariable UUID id, @RequestBody SolicitacaoDTO solicitacaoDTO) {
        Solicitacao solicitacao = solicitacaoService.findById(id);

        if (solicitacao == null) {
            return ResponseEntity.notFound().build();
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        solicitacao.setStatus(solicitacaoDTO.getStatus());
        Solicitacao updatedSolicitacao = solicitacaoService.save(solicitacao);

        SolicitacaoDTO dto = new SolicitacaoDTO(
                solicitacao.getId(),
                solicitacao.getStatus(),
                utilizadorService.findDTOById(solicitacao.getCliente().getId()),
                servicoService.findDTOById(solicitacao.getServico().getId()),
                solicitacao.getData().format(dtf)
        );

        return ResponseEntity.ok(dto);
    }

    /**
     * Apaga uma solicitação.
     *
     * @param id o ID da solicitação
     * @return uma resposta de sucesso ou erro
     */
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
