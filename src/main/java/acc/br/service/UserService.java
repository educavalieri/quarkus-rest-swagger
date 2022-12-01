package acc.br.service;

import acc.br.entities.User;
import acc.br.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

    User getUserById(Long id) throws UserNotFoundException;

    List<User> getAllUsers();

    User updateUser(long id, User user) throws UserNotFoundException;

    User saveUser(User user);

    void deleteUser(long id) throws UserNotFoundException;


}
