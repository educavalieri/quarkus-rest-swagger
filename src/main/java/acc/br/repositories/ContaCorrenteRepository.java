package acc.br.repositories;

import acc.br.entities.ContaCorrente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContaCorrenteRepository implements PanacheRepository<ContaCorrente> {
}

