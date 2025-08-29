package co.com.pragma.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RolRequestDTO {

    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Description is required")
    String description;

}
