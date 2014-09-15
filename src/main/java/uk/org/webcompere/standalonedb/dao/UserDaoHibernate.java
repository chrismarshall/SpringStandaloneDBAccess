package uk.org.webcompere.standalonedb.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import uk.org.webcompere.standalonedb.model.User;

/**
 * Access to the User in the database
 */
public interface UserDaoHibernate extends GenericDAO<User, Integer> {
	User findUserHibernate(String username);
}