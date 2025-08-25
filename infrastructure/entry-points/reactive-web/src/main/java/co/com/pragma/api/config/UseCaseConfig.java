package co.com.pragma.api.config;

import co.com.pragma.api.RequestValidator;
import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import co.com.pragma.api.mapper.UserMapperDTO;
import co.com.pragma.model.user.User;
import co.com.pragma.r2dbc.MyReactiveRepositoryAdapter;
import co.com.pragma.usecase.user.UserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class UseCaseConfig {

private final MyReactiveRepositoryAdapter myReactiveRepositoryAdapter;

    public UseCaseConfig(MyReactiveRepositoryAdapter myReactiveRepositoryAdapter) {
        this.myReactiveRepositoryAdapter = myReactiveRepositoryAdapter;
    }

    @Bean
    public UserUseCase userUseCase() {
        return new UserUseCase(myReactiveRepositoryAdapter);
    }

    @Bean
    public RequestValidator requestValidator() {
        return null;
    }

    @Bean
    public UserMapperDTO userMapperDTO() {
        return new UserMapperDTO() {
            @Override
            public UserResponseDTO toResponse(User user) {
                return null;
            }

            @Override
            public User toModel(UserRequestDTO userRequestDTO) {
                return null;
            }
        };
    }
}
