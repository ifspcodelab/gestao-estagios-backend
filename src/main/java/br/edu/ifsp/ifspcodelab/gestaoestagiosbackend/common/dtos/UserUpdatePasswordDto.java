package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdatePasswordDto {
    @NotNull
    @NotBlank
    String password;
}
