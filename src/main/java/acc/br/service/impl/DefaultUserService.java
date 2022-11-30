package acc.br.service.impl;

import acc.br.entities.User;
import acc.br.exceptions.UserNotFoundException;
import acc.br.repositories.UserRepository;
import acc.br.service.UserService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
@ApplicationScoped
public class DefaultUserService implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public User getUserById(long id) throws UserNotFoundException {
        return userRepository.findByIdOptional(id).orElseThrow(() ->
                new UserNotFoundException("this entity not exist"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.listAll();
    }

    @Override
    @Transactional
    public User updateUser(long id, User user) throws UserNotFoundException {
        User existingUser = getUserById(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAge(user.getAge());
        userRepository.persist(existingUser);
        return existingUser;
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        userRepository.persistAndFlush(user);
        return user;
    }

    @Override
    public void deleteUser(long id) throws UserNotFoundException {
        userRepository.deleteById(id);
    }
}
