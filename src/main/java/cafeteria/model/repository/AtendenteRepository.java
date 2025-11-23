package cafeteria.model.repository;

import cafeteria.model.entities.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtendenteRepository extends JpaRepository<Atendente, Integer> {
    
    // Buscar por parte do nome
    List<Atendente> findByNomeContainingIgnoreCase(String nome);
    
    // Buscar por CPF exato
    Optional<Atendente> findByCpf(String cpf);
    
    // Deletar CPF quando necessario
    void deleteByCpf(String cpf);
}
