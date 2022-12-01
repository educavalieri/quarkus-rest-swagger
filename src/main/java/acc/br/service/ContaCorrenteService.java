package acc.br.service;

import acc.br.entities.ContaCorrente;
import acc.br.entities.User;
import acc.br.exceptions.UserNotFoundException;

import java.util.List;

public interface ContaCorrenteService {

    ContaCorrente getContaById(long id) throws UserNotFoundException;

    List<ContaCorrente> getAllContas();

    ContaCorrente updateConta(long id, ContaCorrente contaCorrente) throws UserNotFoundException;

    ContaCorrente saveConta(ContaCorrente contaCorrente);

    void deleteConta(long id) throws UserNotFoundException;

}
