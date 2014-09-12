package uk.org.webcompere.standalonedb.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Helper class for loading beans out of spring in a standalone context
 * This is threadsafe and puts a singleton pattern over the whole application context.
 * The ideal way to use this is to create a single bean that is wired to the rest of your
 * eco system and then use this class once to instantiate it.
 * 
 * However, it's perfectly possible to call this multiple times from wherever you
 * need particular beans, and you'll get a singleton access to those beans.
 * 
 * You may find this useful if you have multiple application contexts for things like
 * multiple data sources, and you've no way to automatically have those contexts loaded
 * and their beans autowired into in-memory things you'll be using.
 */
public class SpringHelper {
	// all known application contexts
	private static Map<String, ApplicationContext> applicationContexts = new HashMap<>();
	
	/**
	 * Load a bean from the Spring context - caching the spring contexts requested
	 * to guarantee a singleton pattern
	 * @param path path of the application context
	 * @param type type of the bean to load
	 * @return the bean
	 */
	public static <T> T loadFromClassPath(String path, Class<T> type) {
		return getClassPathXmlApplicationContext(path).getBean(type);
	}
	
	/**
	 * Access the application context from the path or cache if it's already been loaded
	 * @param path class path to the context
	 * @return application context
	 */
	public static synchronized ApplicationContext getClassPathXmlApplicationContext(String path) {
		if (!applicationContexts.containsKey(path)) {
			applicationContexts.put(path, new ClassPathXmlApplicationContext(path));
		}
		
		return applicationContexts.get(path);
	}

}
