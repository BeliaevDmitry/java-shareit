package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewUserRequest {
    @NotBlank(message = "Имя пользователя не может быть пустым")
    String name;

    @NotBlank(message = "Email пользователя не может быть пустым")
    @Email(message = "Email имеет неверный формат")
    String email;
}