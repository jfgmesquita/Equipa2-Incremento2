package Equipa2.Incremento2.service;

import java.util.UUID;
import java.util.List;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import Equipa2.Incremento2.model.Servico;
import Equipa2.Incremento2.model.dto.ServicoDTO;
import Equipa2.Incremento2.repository.ServicoRepository;
import Equipa2.Incremento2.exceptions.ResourceNotFoundException;

/**
 * Serviço para operações relacionadas com a entidade Servico.
 */
@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private UtilizadorService utilizadorService;

    /**
     * Encontra todos os serviços.
     *
     * @return uma lista de todos os serviços
     */
    public List<Servico> findAll() {
        return servicoRepository.findAll();
    }

    /**
     * Encontra todos os serviços em formato DTO.
     *
     * @return uma lista de todos os serviços em formato DTO
     */
    public ServicoDTO findDTOById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Servico ser = servicoRepository.findById(id).get();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String formattedDate = ser.getData().format(dtf);

        ServicoDTO servicoDTO = new ServicoDTO();
        servicoDTO.setId(ser.getId());
        servicoDTO.setTipo(ser.getTipo());
        servicoDTO.setDescricao(ser.getDescricao());
        servicoDTO.setData(formattedDate);
        servicoDTO.setProfissional(utilizadorService.findDTOById(ser.getProfissional().getId()));

        return servicoDTO;
    }

    /**
     * Encontra um serviço pelo ID.
     *
     * @param id o ID do serviço
     * @return o serviço com o ID fornecido
     * @throws IllegalArgumentException se o ID for nulo
     * @throws ResourceNotFoundException se o serviço não for encontrado
     */
    public Servico findById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));
    }

    /**
     * Encontra todos os serviços de um profissional.
     *
     * @param profissionalId o ID do profissional
     * @return uma lista de todos os serviços do profissional
     * @throws IllegalArgumentException se o ID for nulo
     */
    public List<Servico> findAllByProfissionalId(UUID profissionalId){
        if (profissionalId == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        return servicoRepository.findAllByProfissionalId(profissionalId);
    }

    /**
     * Guarda um serviço.
     *
     * @param servico o serviço a ser guardado
     * @return o serviço guardado
     * @throws IllegalArgumentException se o serviço for nulo
     */
    public Servico save(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo.");
        }

        return servicoRepository.save(servico);
    }

    /**
     * Atualiza um serviço.
     *
     * @param id o ID do serviço a ser atualizado
     * @param servicoDetails os detalhes do serviço a serem atualizados
     * @return o serviço atualizado
     * @throws IllegalArgumentException se o ID ou os detalhes do serviço forem nulos
     * @throws ResourceNotFoundException se o serviço não for encontrado
     */
    public Servico update(UUID id, Servico servicoDetails) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (servicoDetails == null) {
            throw new IllegalArgumentException("Detalhes do serviço não podem ser nulos.");
        }

        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + id));

        servico.setTipo(servicoDetails.getTipo());
        servico.setDescricao(servicoDetails.getDescricao());
        servico.setData(servicoDetails.getData());
        servico.setValorHora(servicoDetails.getValorHora());

        return servicoRepository.save(servico);
    }

    /**
     * Apaga um serviço pelo ID.
     *
     * @param id o ID do serviço a ser apagado
     * @throws IllegalArgumentException se o ID for nulo
     */
    public void deleteById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        servicoRepository.deleteById(id);
    }

}
