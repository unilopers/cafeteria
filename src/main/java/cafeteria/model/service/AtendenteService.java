package cafeteria.model.service;

import cafeteria.model.entities.Atendente;
import cafeteria.model.repository.AtendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AtendenteService {

    @Autowired
    private AtendenteRepository repository;

    // Listar todos
    public List<Atendente> getAtendentes() {
        return repository.findAll();
    }

    // Consultar por nome (contendo a string)
    public List<Atendente> consultaAtendenteNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    // Consultar por CPF
    public Optional<Atendente> consultaAtendenteCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    // Apagar por ID
    @Transactional
    public void apagarAtendenteId(Integer id) throws Exception {
        if (!repository.existsById(id)) {
            throw new Exception("Atendente não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

    // Novo Atendente
    @Transactional
    public Atendente novoAtendente(Atendente atendente) throws Exception {
        if (atendente.getCpf() != null && repository.findByCpf(atendente.getCpf()).isPresent()) {
            throw new Exception("Já existe um atendente cadastrado com este CPF.");
        }
        return repository.save(atendente);
    }

    // Atualizar Atendente
    @Transactional
    public Atendente atualizarAtendente(Atendente atendente) throws Exception {
        if (atendente.getId() == null || !repository.existsById(atendente.getId())) {
            throw new Exception("Não é possível atualizar. Atendente não encontrado.");
        }
        return repository.save(atendente);
    }
}
