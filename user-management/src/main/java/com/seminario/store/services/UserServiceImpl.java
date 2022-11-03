package com.seminario.store.services;

import com.seminario.store.amqp.RabbitMQMessageProducer;
import com.seminario.store.config.SecurityConfig;
import com.seminario.store.models.User;
import com.seminario.store.repositories.UserRepository;
import commons.exceptions.RecordNotFoundException;
import commons.rest.models.NotificationRequest;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    private final Environment environment;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RabbitMQMessageProducer rabbitMQMessageProducer, Environment environment, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
        this.environment = environment;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        var user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("The user is not found.");
        }
    }

    @Override
    @Transactional
    public User save(User user) {
        user.getAddresses().forEach(user::addAddress);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        var notificationRequest = new NotificationRequest(
                "EMAIL",
                user.getEmail(),
                "Account created successfully",
                "APP"
        );
        rabbitMQMessageProducer.publish(notificationRequest,
                environment.getProperty("rabbitmq.exchanges.internal"),
                environment.getProperty("rabbitmq.routing-keys.internal-notification"));

        return user;
    }

    @Override
    @Transactional
    public User update(User user, Long id) {
        var userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            var userSaved = userOptional.get();
            userSaved.setEmail(user.getEmail());
            userSaved.setRole(user.getRole());
            userSaved.setUsername(user.getUsername());
            userSaved.setPassword(passwordEncoder.encode(user.getPassword()));

            return userSaved;
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public User findByEmail(String email) {
        var user = userRepository.findUserByEmail(email);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("The user is not found.");
        }
    }
}
