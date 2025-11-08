package ru.itis.tutorhub.services.user;

import ru.itis.tutorhub.exceptions.InvalidPasswordException;
import ru.itis.tutorhub.exceptions.UserAlreadyExistsException;
import ru.itis.tutorhub.exceptions.UserNotFoundException;
import ru.itis.tutorhub.models.User;
import ru.itis.tutorhub.repositories.user.UserRepository;
import ru.itis.tutorhub.utils.PasswordHasher;

import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String name, String telegramUsername, String password) {

        //проверка существования пользователя
        if (userRepository.findByTelegramUsername(telegramUsername).isPresent()) {
            throw new UserAlreadyExistsException(telegramUsername);
        }
        //хешируем пароль
        String hashedPassword = PasswordHasher.hash(password);

        //создаем, сохраняем и возвращаем
        return userRepository.save(new User(name, telegramUsername, hashedPassword));
    }

    @Override
    public User login(String telegramUsername, String password) {

        //достаем юзера, если нет, кидаем исключение
        User user = userRepository.findByTelegramUsername(telegramUsername)
                .orElseThrow(() -> new UserNotFoundException(telegramUsername));

        if (!PasswordHasher.verify(password, user.getPassword())) {
            throw new InvalidPasswordException();
        }

        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }
}