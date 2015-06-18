package dk.telenor.javamail.dto;

import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDTO implements UserDetails {

	private static final long serialVersionUID = 1317447527620473688L;

	private long userId;

	@NotNull
	@Size(min = 3, max = 30, message = "Username must be between 3 and 20 characters long.")
	@Pattern(regexp = "^[a-z0-9_-]{3,30}$", message = "User name must be without special symbol and spaces.")
	private String userName;

	@NotNull
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Email address does`t meet pattern.")
	private String email;

	@NotNull
	@Size(min = 6, max = 30, message = "Password must be between 3 and 20 characters long.")
	private String password;

	// Spring Security Fields
	private List<Role> authorities;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;

	private boolean enabled = true;

	public UserDTO() {

	}

	public UserDTO(String name, String pass, String mail) {

		userName = name;
		password = pass;
		email = mail;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return userName;
	}

	public void setName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	//Spring Security User Details Method
	public String getUsername() {
		return email;
	}

	@SuppressWarnings("unchecked")
	public Collection<? extends GrantedAuthority> getAuthorities(){
		return (Collection<? extends GrantedAuthority>) authorities;
	}

	public void setAuthorities(List<Role> authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userName=" + userName
				+ ", eMail=" + email + ", password=" + password
				+ ", authorities=" + authorities + ", accountNonExpired="
				+ accountNonExpired + ", accountNonLocked=" + accountNonLocked
				+ ", credentialsNonExpired=" + credentialsNonExpired
				+ ", enabled=" + enabled + "]";
	}


	
}
