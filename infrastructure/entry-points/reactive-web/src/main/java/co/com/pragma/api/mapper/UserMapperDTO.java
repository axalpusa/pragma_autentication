package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import co.com.pragma.model.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapperDTO {
    UserResponseDTO toResponse(User user);

    User toModel(UserRequestDTO userRequestDTO);
}
