package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateDto {
    @NotNull
    @NotBlank
    String name;
}
