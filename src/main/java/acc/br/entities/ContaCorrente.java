package acc.br.entities;

import io.quarkus.security.jpa.UserDefinition;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "conta_corrente")
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "agencia", nullable = false)
    @NotBlank
    private String agencia;

    @Column(name = "numero", nullable = false)
    @NotBlank
    private String numero;

    @Column(name = "saldo", nullable = false)
    @NotBlank
    private Integer saldo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

