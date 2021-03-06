package com.openclassrooms.oauthserver.model;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority {
    private static final long serialVersionUID = 1L;
	
	private String 	authority;
	
	public Authorities(){

	}

	public Authorities(ERoles role) {
		super();
		authority = role.name();
	}

	@Override
    public String getAuthority() {
        return authority;
    }

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Authorities{" +
				"authority='" + authority + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Authorities other = (Authorities) obj;
		if (authority == null) {
			if (other.authority != null) {
				return false;
			}
		} else if (!authority.equals(other.authority)) {
			return false;
		}
		return true;
	}

	
	
}
