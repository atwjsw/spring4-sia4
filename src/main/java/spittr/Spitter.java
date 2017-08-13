package spittr;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

public class Spitter {

  private Long id;
  
  @NotNull
//  @Size(min=5, max=16, message="Username must be between {min} and {max} characters long.")
  @Size(min=5, max=25, message = "{username.size}")
  private String username;

  @NotNull
//  @Size(min=5, max=25, message = "Password must be between {min} and {max} characters long.")
  @Size(min=5, max=25, message = "{password.size}")
  private String password;
  
  @NotNull
//  @Size(min=2, max=30, message="First name must be between {min} and {max} characters long.")
  @Size(min=5, max=25, message = "{firstName.size}")
  private String firstName;

  @NotNull
//  @Size(min=2, max=30, message="Last name must be between {min} and {max} characters long.")
  @Size(min=5, max=25, message = "{lastName.size}")
  private String lastName;
  
  @NotNull
  @Email(message="The email address must be valid.")
  private String email;

  public Spitter() {}
  
  public Spitter(String username, String password, String firstName, String lastName, String email) {
    this(null, username, password, firstName, lastName, email);
  }

  public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object that) {
    return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
  }
  
  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
  }

  @Override
  public String toString() {
    return "Spitter{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
