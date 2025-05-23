package ru.practicum.shareit.user.repository;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Repository;
import ru.practicum.shareit.user.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserRepositoryInMemoryImpl implements UserRepository {
    Map<Long, User> users = new HashMap<>();
    Map<String, User> usersByEmail = new HashMap<>();

    @NonFinal
    Long globalUserId = 0L;

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public User save(User user) {
        user.setId(generateId());
        users.put(user.getId(), user);
        usersByEmail.put(user.getEmail(), user);
        return users.get(user.getId());
    }

    @Override
    public Optional<User> findById(Long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    @Override
    public User update(User user) {
        Long userId = user.getId();
        String checkEmail = users.get(userId).getEmail();
        if (!checkEmail.equals(user.getEmail())) {
            usersByEmail.remove(checkEmail);
        }
        usersByEmail.put(user.getEmail(), user);
        users.put(userId, user);
        return users.get(userId);
    }

    @Override
    public void deleteById(Long userId) {
        usersByEmail.remove(users.get(userId).getEmail());
        users.remove(userId);
    }

    private Long generateId() {
        return ++globalUserId;
    }
}