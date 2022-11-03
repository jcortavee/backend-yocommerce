package com.seminario.store.services;

import commons.exceptions.UserDoesNotExistException;
import com.seminario.store.models.User;
import com.seminario.store.repositories.UserRepository;
import commons.exceptions.UserAlreadyExistsException;
import com.seminario.store.models.DeliveryPerson;
import com.seminario.store.repositories.DeliveryPersonRepository;
import commons.exceptions.RecordNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;
    private final UserRepository userRepository;

    public DeliveryPersonServiceImpl(DeliveryPersonRepository deliveryPersonRepository, UserRepository userRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DeliveryPerson> findAll() {
        return deliveryPersonRepository.findAll();
    }

    @Override
    public DeliveryPerson findById(Long id) {
        var deliveryPerson = deliveryPersonRepository.findById(id);

        if (deliveryPerson.isPresent()) {
            return deliveryPerson.get();
        } else {
            throw new RecordNotFoundException("The delivery person is not found.");
        }
    }

    @Override
    @Transactional
    public DeliveryPerson save(DeliveryPerson deliveryPerson) {
        Optional<DeliveryPerson> optionalDeliveryPerson =
                deliveryPersonRepository.findByUserId(deliveryPerson.getUser().getId());
        if (optionalDeliveryPerson.isEmpty()) {
            Optional<User> optionalUser = userRepository
                    .findById(deliveryPerson.getUser().getId());

            if (optionalUser.isEmpty()) {
                throw  new UserDoesNotExistException("User does not exists");
            } else {
                return deliveryPersonRepository.save(deliveryPerson);
            }
        } else {
            throw new UserAlreadyExistsException("User already exists.");
        }
    }

    @Override
    @Transactional
    public DeliveryPerson update(DeliveryPerson deliveryPerson, Long id) {
        var deliveryPersonFetched = findById(id);

        deliveryPersonFetched.setDriverLicense(deliveryPerson.getDriverLicense());
        deliveryPersonFetched.setStatus(deliveryPerson.getStatus());
        deliveryPersonFetched.setAvailable(deliveryPerson.getAvailable());

        return deliveryPersonFetched;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        var deliveryPersonFetched = findById(id);
        deliveryPersonRepository.delete(deliveryPersonFetched);
    }
}
