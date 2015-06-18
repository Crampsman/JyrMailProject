package dk.telenor.javamail.dto;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private long roleId;

	private long userId;

	private String role;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.role;
	}

	@Override
	public String toString() {
		return "RoleDTO [roleId=" + roleId + ", userId=" + userId + ", role="
				+ role + "]";
	}

}
