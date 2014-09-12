package uk.org.webcompere.standalonedb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.org.webcompere.standalonedb.dao.UserDao;
import uk.org.webcompere.standalonedb.model.User;

import java.util.List;

/**
 * Implementation of user service
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
	@Transactional(readOnly = true)
    public User findUser(Integer userId) {
        return userDao.find(userId);
    }


	@Override
	@Transactional(readOnly = true)
	public User findUser(String username, String password) {
		return userDao.findByLoginCredentials(username, password);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUser(String username) {
		return userDao.findByUserName(username);
	}
    
    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(Integer userId) {
        userDao.removeById(userId);
    }

	@Override
	@Transactional
	public void deleteAll() {
		for(User user:userDao.findAll()) {
			userDao.remove(user);
		}
		
	}

}
