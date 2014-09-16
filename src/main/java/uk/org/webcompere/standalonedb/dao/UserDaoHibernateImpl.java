package uk.org.webcompere.standalonedb.dao;

import com.googlecode.genericdao.search.Search;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uk.org.webcompere.standalonedb.model.User;

/**
 * Implementation of the UserDaoClass
 */
@Repository
@Transactional
public class UserDaoHibernateImpl extends BaseDaoImpl<User, Integer> implements
		UserDaoHibernate {

	@Override
	public User findByUserNameHibernate(String username) {
		Search criteria = new Search();
		criteria.addFilterEqual("username", username);

		return singleResultOf(criteria);
	}
}