package cafeteria.model.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "atendente")
public class Atendente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome", nullable = false, length = 30)
    private String nome;

    @Column(name = "cpf", nullable = false, length = 14, unique = true) 
    private String cpf;

    @Column(name = "email", nullable = false, length = 50) 
    private String email;

    // Construtor vazio
    public Atendente() {
    }

    // Construtor com argumentos
    public Atendente(Integer id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
