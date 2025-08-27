package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.response.OrderResponseDTO;
import co.com.pragma.model.typeloan.TypeLoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeLoanMapperDTO {

    OrderResponseDTO toResponse(TypeLoan typeLoan);

}
