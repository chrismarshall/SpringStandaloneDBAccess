package uk.org.webcompere.standalonedb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uk.org.webcompere.standalonedb.model.User;

import com.googlecode.genericdao.search.Search;

/**
 * Implementation of the UserDaoClass
 */
@Repository
@Transactional
public class UserDaoHibernateImpl extends BaseDaoImpl<User, Integer> implements UserDaoHibernate {
	@Override
	public User findUserHibernate(String username) {
		Search criteria = new Search();
		criteria.addFilterEqual("username", username);
		return singleResultOf(criteria);
	}
}