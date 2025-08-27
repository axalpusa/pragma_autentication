package co.com.pragma.api.config;

import co.com.pragma.model.order.gateways.OrderRepository;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.usecase.order.OrderUseCase;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import org.springframework.context.annotation.Bean;

public class UseCasesConfig {
    @Bean
    public IOrderUseCase userUseCase(OrderRepository orderRepository, TypeLoanRepository loanRepository) {
        return new OrderUseCase(orderRepository, loanRepository);
    }
}
