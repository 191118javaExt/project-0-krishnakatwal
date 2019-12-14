package com.revature.models;

public class User {
	private int id;
	private String first_name;
	private String last_name;
	private String password;
	private boolean isEmployee;
	private boolean isAdmin;

	public User() {
		super();

	}

	public User(int id, String first_name, String last_name, String password, boolean isEmployee, boolean isAdmin) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
		this.isEmployee = isEmployee;
		this.isAdmin = isAdmin;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + id;
		result = prime * result + (isAdmin ? 1231 : 1237);
		result = prime * result + (isEmployee ? 1231 : 1237);
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		User other = (User) obj;
		if (first_name == null) {
			if (other.first_name != null)
				return false;

		} else if (!first_name.equals(other.first_name))
			return false;

		if (id != other.id)
			return false;

		if (isAdmin != other.isAdmin)
			return false;

		if (isEmployee != other.isEmployee)
			return false;

		if (last_name == null) {
			if (other.last_name != null)
				return false;

		} else if (!last_name.equals(other.last_name))
			return false;

		if (password == null) {
			if (other.password != null)
				return false;

		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", password=" + password
				+ ", isEmployee=" + isEmployee + ", isAdmin=" + isAdmin + "]";
	}

}
