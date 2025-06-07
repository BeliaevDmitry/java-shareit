package ru.practicum.shareit.item;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.*;
import ru.practicum.shareit.item.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ItemController {
    ItemService itemService;
    public static final String USER_ID_HEADER = "X-Sharer-User-Id";

    @GetMapping
    public List<ItemDto> findAllOwnerItems(@RequestHeader(USER_ID_HEADER) Long userId) {
        return itemService.findItemsByOwnerId(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> findItemsByNameOrDescription(@RequestHeader(USER_ID_HEADER) Long userId,
                                                      @RequestParam(value = "text", required = false) String text) {
        return itemService.findItemsByNameOrDescription(userId, text);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItemById(@RequestHeader(USER_ID_HEADER) Long userId, @PathVariable("itemId") Long itemId) {
        return itemService.findById(userId, itemId);
    }

    @PostMapping
    public ItemDto create(@RequestHeader(USER_ID_HEADER) Long userId,
                          @RequestBody NewItemDto newItemDto) {
        return itemService.create(userId, newItemDto);
    }

    @PatchMapping("{itemId}")
    public ItemDto update(@RequestHeader(USER_ID_HEADER) Long userId,
                          @PathVariable("itemId") Long itemId,
                          @RequestBody UpdateItemDto updateItemDto) {
        return itemService.update(userId, itemId, updateItemDto);
    }

    @PostMapping("{itemId}/comment")
    public CommentDto addComment(@RequestHeader(USER_ID_HEADER) Long userId,
                                 @PathVariable("itemId") Long itemId,
                                 @RequestBody NewCommentDto newCommentDto) {
        return itemService.addComment(userId, itemId, newCommentDto);
    }
}