package uk.org.webcompere.standalonedb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.org.webcompere.standalonedb.model.User;

/**
 * Access to the User in the database
 */
public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

}