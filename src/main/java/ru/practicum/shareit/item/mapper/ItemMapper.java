package ru.practicum.shareit.item.mapper;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.NewItemRequest;
import ru.practicum.shareit.item.dto.UpdateItemRequest;
import ru.practicum.shareit.item.model.Item;

public class ItemMapper {
    public static ItemDto mapToItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .rentalCount(item.getRentalCount())
                .available(item.getAvailable())
                .build();
    }

    public static ItemDto mapToItem(ItemDto itemDto) {
        return ItemDto.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .rentalCount(itemDto.getRentalCount())
                .available(itemDto.getAvailable())
                .build();
    }

    public static Item mapToItemNew(NewItemRequest newItemRequest) {
        return Item.builder()
                .name(newItemRequest.getName())
                .description(newItemRequest.getDescription())
                .available(newItemRequest.getAvailable())
                .build();
    }

    public static Item mapToItemUpdate(Item item, UpdateItemRequest updateItemRequest) {

        if (updateItemRequest.hasName()) {
            item.setName(updateItemRequest.getName());
        }

        if (updateItemRequest.hasDescription()) {
            item.setDescription(updateItemRequest.getDescription());
        }

        if (updateItemRequest.hasAvailable()) {
            item.setAvailable(updateItemRequest.getAvailable());
        }

        return item;
    }
}

