package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Value
public class UserAdvisorUpdateDto {
    @NotNull
    @NotBlank
    String name;

    @NotNull
    @NotBlank
    @Email
    String email;

    @NotNull
    List<UUID> coursesIds;
}
