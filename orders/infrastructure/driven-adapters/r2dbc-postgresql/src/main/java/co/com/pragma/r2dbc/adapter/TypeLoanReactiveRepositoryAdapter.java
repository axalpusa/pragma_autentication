package co.com.pragma.r2dbc.adapter;

import co.com.pragma.model.typeloan.TypeLoan;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.r2dbc.adapter.interfaces.TypeLoanReactiveRepository;
import co.com.pragma.r2dbc.entities.TypeLoanEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class TypeLoanReactiveRepositoryAdapter extends ReactiveAdapterOperations <
        TypeLoan, TypeLoanEntity, Long, TypeLoanReactiveRepository
        > implements TypeLoanRepository {
    public TypeLoanReactiveRepositoryAdapter(TypeLoanReactiveRepository repository, ObjectMapper mapper) {
        super ( repository, mapper, d -> mapper.mapBuilder ( d, TypeLoan.TypeLoanBuilder.class ).build ( ) );
    }

    /**
     * Search type loan for id.
     *
     * @param idTypeLoan search by id
     * @return Mono<TypeLoan> TypeLoan found or empty
     */
    @Override
    public Mono < TypeLoan > getByIdTypeLoan(Long idTypeLoan) {
        return repository.findByIdTypeLoan ( idTypeLoan )
                .map ( entity -> mapper.map ( entity, TypeLoan.class ) )
                .doOnError ( e -> log.error ( "Error search type loan by id: {}", e.getMessage ( ), e ) );
    }

}
