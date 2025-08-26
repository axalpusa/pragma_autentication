package co.com.pragma.api.config;

import co.com.pragma.model.user.gateways.UserRepository;
import co.com.pragma.usecase.user.UserUserCase;
import co.com.pragma.usecase.user.interfaces.IUserUseCase;
import org.springframework.context.annotation.Bean;

public class UseCasesConfig {
    @Bean
    public IUserUseCase userUseCase(UserRepository repository) {
        return new UserUserCase (repository);
    }
}
