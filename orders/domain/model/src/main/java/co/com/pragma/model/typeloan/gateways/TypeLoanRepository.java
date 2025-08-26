package co.com.pragma.model.typeloan.gateways;

import co.com.pragma.model.order.Order;
import co.com.pragma.model.typeloan.TypeLoan;
import reactor.core.publisher.Mono;

public interface TypeLoanRepository {
    Mono < Boolean > existByIdTypeLon(Long idTypeLoan);
    Mono < Boolean > betwenMaximum(Long idTypeLoan);
}
