package uk.org.webcompere.standalonedb.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import uk.org.webcompere.standalonedb.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Common methods and helpers to add to generic dao
 */
public abstract class BaseDaoImpl<TYPE,ID extends Serializable> extends GenericDAOImpl<TYPE,ID> {
    @Autowired
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<TYPE> findAll() {
        try {
            return super.findAll();
        } catch (NullPointerException e) {
            // there is a bug in the base class when fetching from an empty table
            // it results in the above caught exception - the workaround is to return nothing
            return new ArrayList<TYPE>();
        }
    }

    /**
     * Provide a single result from a query
     * @param criteria criteria
     * @return one result or null
     */
    protected User singleResultOf(Search criteria) {
        List<?> results = search(criteria);
        if (results.size()>0 && results.get(0) instanceof User) {
            return (User)results.get(0);
        }
        return null;
    }
}
