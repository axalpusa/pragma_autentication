package co.com.pragma.r2dbc;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.entities.UserEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations <
        User, UserEntity, Long, UserReactiveRepository
        > implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super ( repository, mapper, d -> mapper.mapBuilder ( d, User.UserBuilder.class ).build ( ) );
    }

    @Override
    @Transactional
    public Mono < User > save(User user) {
        UserEntity entity = mapper.map ( user, UserEntity.class );
        return repository.save ( entity )
                .map ( saved -> mapper.map ( saved, User.class ) )
                .doOnError ( e -> log.error ( "Error al guardar usuario en BD: {}", e.getMessage ( ), e ) );
    }

    @Override
    public Mono < User > findByEmailAddress(String email) {
        return repository.findByEmailAddress ( email )
                .map ( entity -> mapper.map ( entity, User.class ) )
                .doOnError ( e -> log.error ( "Error al buscar usuario por email: {}", e.getMessage ( ), e ) );
    }
    @Override
    public Flux<User> findAll() {
        return repository.findAll()
                .map(this::toUser);
    }
    private User toUser(UserEntity entity) {
        return mapper.map(entity, User.class);
    }
}
