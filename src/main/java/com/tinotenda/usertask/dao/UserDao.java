package com.tinotenda.usertask.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDao {
    @Schema(name = "username")
    @JsonProperty("username")
    private String userName;

    @NotBlank(message = "last_name is mandatory")
    @Schema(required = true, name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank(message = "first_name is mandatory")
    @Schema(required = true)
    @JsonProperty("first_name")
    private String firstName;

}
