package uk.org.webcompere.standalonedb.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import uk.org.webcompere.standalonedb.model.User;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;

/**
 * Common methods and helpers to add to generic dao
 */
public abstract class BaseDaoImpl<TYPE, ID extends Serializable> extends
		GenericDAOImpl<TYPE, ID> {

	@PersistenceContext
	private EntityManager entityManager;

	// Need the EntityManagerFactory to pull the SessionFactory, this lets
	// HibernateBaseDao cache it's HibernateSearchProcessor and
	// HibernateMetadataUtil per session factory.
	@Autowired
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		HibernateEntityManagerFactory hib = (HibernateEntityManagerFactory) entityManagerFactory;
		super.setSessionFactory(hib.getSessionFactory());
	}

	// Need to unwrap the session each time, as the underlying SessionFactory
	// won't have the currentSession() set since it's running in JPA mode.
	@Override
	protected Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List<TYPE> findAll() {
		try {
			return super.findAll();
		} catch (NullPointerException e) {
			// there is a bug in the base class when fetching from an empty
			// table it results in the above caught exception - the workaround is to
			// return nothing
			return new ArrayList<TYPE>();
		}
	}

	/**
	 * Provide a single result from a query
	 *
	 * @param criteria
	 *            criteria
	 * @return one result or null
	 */
	protected User singleResultOf(Search criteria) {
		List<?> results = search(criteria);
		if (results.size() > 0 && results.get(0) instanceof User) {
			return (User) results.get(0);
		}
		return null;
	}
}