package acc.br.dtos;

import acc.br.entities.ContaCorrente;
import acc.br.entities.User;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class ContaCorrenteDto {

    @Schema(title = "Agencia", required = true)
    private String agencia;
    @Schema(title = "Numero da conta", required = true)
    private String numero;
    @Schema(title = "Saldo", required = true)
    private Integer saldo;
    @Schema(title = "Id do usuário", required = true)
    private Long user_id;

    public ContaCorrenteDto() {
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

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    ContaCorrenteDto toDto(ContaCorrente contaCorrente){
        ContaCorrenteDto dto = new ContaCorrenteDto();
        dto.setAgencia(contaCorrente.getAgencia());
        dto.setNumero(contaCorrente.getNumero());
        dto.setSaldo(contaCorrente.getSaldo());
        dto.setUser_id(contaCorrente.getId());

        return dto;
    }



}
