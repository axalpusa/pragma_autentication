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

/**
 * Adapter for user persistence operations using R2DBC.
 */
@Slf4j
@Component
public class UserReactiveRepositoryAdapter extends ReactiveAdapterOperations <
        User, UserEntity, Long, UserReactiveRepository
        > implements UserRepository {
    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper) {
        super ( repository, mapper, d -> mapper.mapBuilder ( d, User.UserBuilder.class ).build ( ) );
    }

    /**
     * Saves a user to the database transactional.
     *
     * @param user new user
     * @return Mono<User> new user
     */
    @Override
    @Transactional
    public Mono < User > save(User user) {
        UserEntity entity = mapper.map ( user, UserEntity.class );
        return repository.save ( entity )
                .map ( saved -> mapper.map ( saved, User.class ) )
                .doOnError ( e -> log.error ( "Error saving user to database: {}", e.getMessage ( ), e ) );
    }

    /**
     * Search user for email address.
     *
     * @param email search by email address
     * @return Mono<User> User found or empty
     */
    @Override
    public Mono < User > findByEmailAddress(String email) {
        return repository.findByEmailAddress ( email )
                .map ( entity -> mapper.map ( entity, User.class ) )
                .doOnError ( e -> log.error ( "Error search user by email address: {}", e.getMessage ( ), e ) );
    }

    @Override
    public Flux < User > findAll() {
        return repository.findAll ( )
                .map ( this::toUser );
    }

    private User toUser(UserEntity entity) {
        return mapper.map ( entity, User.class );
    }
}
