package spittr.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Spitter implements Serializable {
	
	private Spitter() {}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="fullname")
	private String fullName;

	@Column(name="email")
	private String email;

	@Column(name="updateByEmail")
	private boolean updateByEmail;

	public Spitter(Long id, String username, String password, String fullName,
				   String email, boolean updateByEmail) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.updateByEmail = updateByEmail;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isUpdateByEmail() {
		return updateByEmail;
	}

	@Override
	public String toString() {
		return "Spitter{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", fullName='" + fullName + '\'' +
				", email='" + email + '\'' +
				", updateByEmail=" + updateByEmail +
				'}';
	}
}
