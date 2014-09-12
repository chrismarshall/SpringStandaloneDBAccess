package uk.org.webcompere.readwrite;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import uk.org.webcompere.standalonedb.helper.SpringHelper;
import uk.org.webcompere.standalonedb.model.User;
import uk.org.webcompere.standalonedb.service.UserService;

/**
 * This unit test demonstrates that the data access layer is working. The idea
 * of using a real database in a unit test is frowned upon. If you wanted to test
 * your data access layer, you would be better off using H2, defined in a testing
 * version of the Spring context.
 * 
 * Similarly, if you're testing a Spring app, you should use the SpringJUnit4ClassRunner
 * to load the necessary application contexts and autowire your beans up.
 * 
 * This codebase is demonstrating three things.
 * 
 * - How to have a Spring/Hibernate data access layer without a web container to load the app context
 *     (and how to have multiple spring contexts on the go simultaneously if you had, say multiple data sources)
 * - How to use a real database as part of a unit test
 *     (and how to wire in some spring contexts to a unit test that CANNOT use the SpringJUnit4ClassRunner
 *     because it needs to use some other class runner, e.g. Cucumber)
 * - What boilerplate do you need to have Spring and Hibernate running together
 */
public class ReadAndWriteFromDb {
	private static final String USERNAME="username1";
	private static final String PASSWORD="p455w0rd";
	
	private UserService userService;
	
	@Before
	public void before() {
		userService = SpringHelper.loadFromClassPath("/applicationContext.xml", UserService.class);
		
		// clear all users out of the test db
		userService.deleteAll();
	}
	
	@Test
	public void demonstrateStoringAndRetrievingData() {
		User newUser = createAndSaveUser();
		User savedUser = loadUser(newUser);
		
		assertThat(savedUser, is(newUser));
	}
	
	@Test
	public void cannotFindNonExistentUser() {
		assertNull(userService.findUser(12345));
	}
	
	@Test
	public void findUserByUsernameAndPassword() {
		// demonstrate how easy it is to do a query
		User newUser = createAndSaveUser();
		
		assertNull(userService.findUser(USERNAME, "wrong password"));
		assertThat(userService.findUser(USERNAME, PASSWORD), is(newUser));
		
	}

	private User loadUser(User newUser) {
		Integer id = newUser.getId();
		
		User savedUser = userService.findUser(id);
		return savedUser;
	}

	private User createAndSaveUser() {
		User newUser = new User();
		newUser.setFirstName("My name");
		newUser.setUsername(USERNAME);
		newUser.setPassword(PASSWORD);
		
		userService.save(newUser);
		return newUser;
	}
}
