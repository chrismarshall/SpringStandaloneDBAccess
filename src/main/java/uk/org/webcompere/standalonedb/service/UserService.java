package uk.org.webcompere.standalonedb.service;

import uk.org.webcompere.standalonedb.model.User;

import java.util.List;

/**
 * Allows the controller to interact with users
 */
public interface UserService {
    /**
     * Provide all users
     * @return
     */
    List<User> getAllUsers();

    /**
     * Find a user by ID or return null
     * @param userId
     * @return
     */
    User findUser(Integer userId);

    /**
     * Save a user
     * @param user
     */
    void save(User user);

    /**
     * Delete a user
     * @param userId id to delete
     */
    void delete(Integer userId);
    
    /**
     * Delete all users
     */
    void deleteAll();
}
