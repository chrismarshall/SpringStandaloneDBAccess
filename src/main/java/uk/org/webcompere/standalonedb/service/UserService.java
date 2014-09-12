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

    /**
     * Find a user by username and password
     * @param username username
     * @param password password
     * @return the found user or null if not found
     */
	User findUser(String username, String password);
}
