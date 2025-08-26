package co.com.pragma.api.mapper;

import co.com.pragma.api.dto.request.UserRequestDTO;
import co.com.pragma.api.dto.response.UserResponseDTO;
import co.com.pragma.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapperDTO {

    UserResponseDTO toResponse(User user);

    @Mapping(target = "idUser", ignore = true)
    User toModel(UserRequestDTO userRequestDTO);
}
