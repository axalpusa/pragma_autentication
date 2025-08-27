package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.request.OrderRequestDTO;
import co.com.pragma.api.dto.response.OrderResponseDTO;
import co.com.pragma.model.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapperDTO {

    OrderResponseDTO toResponse(Order order);

    @Mapping(target = "idOrder", ignore = true)
    Order toModel(OrderRequestDTO orderRequestDTO);
}
