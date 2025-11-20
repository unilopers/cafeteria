package cafeteria.model.repository;

import cafeteria.model.entities.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    //Busca exata
    List<Cliente> findByNome(String nome);
    void deleteByCpf(String cpf);
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findByEndereco(String endereco);

    //Busca Parcial
    List<Cliente> findByNomeContainingIgnoreCase(String nome);
    List<Cliente> findByEnderecoContainingIgnoreCase(String endereco);
}
