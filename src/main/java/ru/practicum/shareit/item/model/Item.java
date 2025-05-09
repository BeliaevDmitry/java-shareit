package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * TODO Sprint add-controllers.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Item {
    @NotNull
    Long id;

    @NotBlank
    String name;

    @NotBlank
    String description;

    Boolean available;

    @NotNull
    Long ownerId;

    Long requestId;

    int rentalCount;
}