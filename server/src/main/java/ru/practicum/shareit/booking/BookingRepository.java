package ru.practicum.shareit.booking;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Репозиторий для работы с бронированиями.
 * Предоставляет методы для поиска бронирований по различным критериям.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // === Методы для владельца вещи (owner) ===

    /**
     * Находит все бронирования для указанного владельца вещи.
     *
     * @param ownerId   ID владельца вещи
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByItemOwnerId(Long ownerId, Sort sortOrder);

    /**
     * Находит бронирования для владельца вещи с определённым статусом.
     *
     * @param ownerId   ID владельца вещи
     * @param status    статус бронирования
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByItemOwnerIdAndStatus(Long ownerId, BookingStatus status, Sort sortOrder);

    /**
     * Находит текущие бронирования для владельца вещи (активные на данный момент).
     *
     * @param ownerId   ID владельца вещи
     * @param now1      текущее время (для проверки начала)
     * @param now2      текущее время (для проверки окончания)
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByItemOwnerIdAndStartLessThanEqualAndEndGreaterThanEqual(
            Long ownerId, LocalDateTime now1, LocalDateTime now2, Sort sortOrder);

    /**
     * Находит будущие бронирования для владельца вещи.
     *
     * @param ownerId   ID владельца вещи
     * @param now       текущее время (для сравнения с началом бронирования)
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByItemOwnerIdAndStartAfter(Long ownerId, LocalDateTime now, Sort sortOrder);

    /**
     * Находит завершённые бронирования для владельца вещи.
     *
     * @param ownerId   ID владельца вещи
     * @param now       текущее время (для сравнения с окончанием бронирования)
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByItemOwnerIdAndEndBefore(Long ownerId, LocalDateTime now, Sort sortOrder);

    // === Методы для арендатора (booker) ===

    /**
     * Находит все бронирования для указанного арендатора.
     *
     * @param bookerId  ID арендатора
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByBookerId(Long bookerId, Sort sortOrder);

    /**
     * Находит бронирования арендатора с определённым статусом (например, WAITING).
     *
     * @param bookerId  ID арендатора
     * @param status    статус бронирования
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByBookerIdAndStatus(Long bookerId, BookingStatus status, Sort sortOrder);

    /**
     * Находит бронирования арендатора с одним из указанных статусов (например, REJECTED или CANCELED).
     *
     * @param bookerId  ID арендатора
     * @param statuses  список статусов
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByBookerIdAndStatusIn(Long bookerId, List<BookingStatus> statuses, Sort sortOrder);

    /**
     * Находит текущие бронирования арендатора (активные на данный момент).
     *
     * @param bookerId  ID арендатора
     * @param now1      текущее время (для проверки начала)
     * @param now2      текущее время (для проверки окончания)
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByBookerIdAndStartLessThanEqualAndEndGreaterThanEqual(
            Long bookerId, LocalDateTime now1, LocalDateTime now2, Sort sortOrder);

    /**
     * Находит будущие бронирования арендатора.
     *
     * @param bookerId  ID арендатора
     * @param now       текущее время (для сравнения с началом бронирования)
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByBookerIdAndStartAfter(Long bookerId, LocalDateTime now, Sort sortOrder);

    /**
     * Находит завершённые бронирования арендатора.
     *
     * @param bookerId  ID арендатора
     * @param now       текущее время (для сравнения с окончанием бронирования)
     * @param sortOrder порядок сортировки
     * @return список бронирований
     */
    List<Booking> findAllByBookerIdAndEndBefore(Long bookerId, LocalDateTime now, Sort sortOrder);

    // === Дополнительные методы ===

    /**
     * Находит все бронирования для указанной вещи.
     *
     * @param itemId ID вещи
     * @return список бронирований
     */
    List<Booking> findAllByItemId(Long itemId);

    /**
     * Находит завершённые бронирования указанной вещи пользователем.
     * Используется для проверки, оставлял ли пользователь отзыв.
     *
     * @param itemId          ID вещи
     * @param userId          ID пользователя
     * @param now             текущее время (для проверки окончания)
     * @param bookingStatus   статус бронирования (обычно APPROVED)
     * @return список бронирований
     */
    List<Booking> findAllByItemIdAndBookerIdAndEndBeforeAndStatus(
            Long itemId, Long userId, LocalDateTime now, BookingStatus bookingStatus);
}