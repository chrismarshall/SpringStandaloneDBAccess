package uk.org.webcompere.standalonedb.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Representation of a user - way too simple!
 */
@Entity
@Table(name="usertable")
public class User implements Serializable {
	@Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username", unique=true)
    private String username;
    private String password;
    private String emailAddress;
    private String firstName;
    private String lastName;

    @Override
    public String toString() {
        return "User[ username:" + username + " - id:" + id + " ];";
    }


    public String getUsername() {
        return username;
    }


    public void setUsername( String username ) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword( String password ) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User() {

    }

    public User( String username, String password, String emailAddress ) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress( String emailAddress ) {
        this.emailAddress = emailAddress;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (emailAddress != null ? !emailAddress.equals(user.emailAddress) : user.emailAddress != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (id != null ? !id.equals(user.id) : user.id != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}