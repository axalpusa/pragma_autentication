package co.com.pragma.config;

import co.com.pragma.model.order.gateways.OrderRepository;
import co.com.pragma.model.typeloan.gateways.TypeLoanRepository;
import co.com.pragma.usecase.order.OrderUseCase;
import co.com.pragma.usecase.order.interfaces.IOrderUseCase;
import co.com.pragma.usecase.typeloan.TypeloanUseCase;
import co.com.pragma.usecase.typeloan.interfaces.ITypeLoanUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.pragma.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
    /*@Bean
    public ITypeLoanUseCase typeLoanUseCase(TypeLoanRepository repository) {
        return new TypeloanUseCase ( repository );
    }*/

    /*@Bean
    public IOrderUseCase orderUseCaseBean(OrderRepository repository,
                                      TypeLoanRepository typeLoanRepository,
                                      ITypeLoanUseCase typeloanUseCase) {
        return new OrderUseCase ( repository, typeLoanRepository, typeloanUseCase );
    }*/
}
