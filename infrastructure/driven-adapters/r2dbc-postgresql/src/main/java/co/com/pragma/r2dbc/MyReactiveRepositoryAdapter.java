package co.com.pragma.r2dbc;

import co.com.pragma.model.user.User;
import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.r2dbc.entities.UserEntity;
import co.com.pragma.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserEntity,
        Long,
        MyReactiveRepository
        > implements UserRepository {
    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.mapBuilder(d, User.UserBuilder.class).build());
    }


    @Override
    public Mono<User> findByIdUser(Long idUser) {
        return repository.findByIdUser(idUser);
    }

    @Override
    public Mono<User> saveUser(User user) {
       return repository.saveUser(user);
    }

    @Override
    public Mono<Void> deleteUserByIdUser(Long idUser) {
        return repository.deleteUserByIdUser(idUser);
    }

    @Override
    public Mono<Boolean> existEmailAddress(String emailAddress) {
        return repository.existEmailAddress(emailAddress);
    }

    @Override
    public Flux<User> findAllUsers() {
        return repository.findAllUsers();
    }
}
