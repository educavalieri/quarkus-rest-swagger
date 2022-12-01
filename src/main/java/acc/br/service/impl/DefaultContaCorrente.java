package acc.br.service.impl;

import acc.br.entities.ContaCorrente;
import acc.br.exceptions.UserNotFoundException;
import acc.br.repositories.ContaCorrenteRepository;
import acc.br.service.ContaCorrenteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DefaultContaCorrente implements ContaCorrenteService {

    @Inject
    private ContaCorrenteRepository contaCorrenteRepository;

    @Override
    public ContaCorrente getContaById(long id) throws UserNotFoundException {
        return contaCorrenteRepository.findByIdOptional(id).orElseThrow(() ->
                new UserNotFoundException("this CC not exist"));
    }

    @Override
    public List<ContaCorrente> getAllContas() {
        return contaCorrenteRepository.listAll();
    }

    @Override
    public ContaCorrente updateConta(long id, ContaCorrente contaCorrente) throws UserNotFoundException {
        var entity = getContaById(id);
        entity.setUser(contaCorrente.getUser());
        entity.setNumero(contaCorrente.getNumero());
        entity.setAgencia(contaCorrente.getAgencia());
        entity.setSaldo(contaCorrente.getSaldo());
        return entity;
    }

    @Override
    public ContaCorrente saveConta(ContaCorrente contaCorrente) {
        contaCorrenteRepository.persistAndFlush(contaCorrente);
        return contaCorrente;
    }

    @Override
    public void deleteConta(long id) throws UserNotFoundException {
        contaCorrenteRepository.deleteById(id);
    }
}
