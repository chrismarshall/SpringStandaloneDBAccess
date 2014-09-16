package uk.org.webcompere.standalonedb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import uk.org.webcompere.standalonedb.dao.UserDaoHibernate;
import uk.org.webcompere.standalonedb.model.User;
import uk.org.webcompere.standalonedb.repository.UserDao;

/**
 * Implementation of user service
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoHibernate userDaoHibernate;

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
	@Transactional(readOnly = true)
    public User findUser(Integer userId) {
        return userDao.findOne(userId);
    }


	@Override
	@Transactional(readOnly = true)
	public User findUser(String username, String password) {
		return userDao.findByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUser(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserHibernate(String username) {
		return userDaoHibernate.findByUserNameHibernate(username);
	}

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional
    public void delete(Integer userId) {
        userDao.delete(userId);
    }

	@Override
	@Transactional
	public void deleteAll() {
		for(User user:userDao.findAll()) {
			userDao.delete(user);
		}

	}

}
