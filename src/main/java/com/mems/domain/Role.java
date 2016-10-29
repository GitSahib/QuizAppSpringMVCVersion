package com.mems.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends Model {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    @ManyToMany(mappedBy="roles",fetch=FetchType.EAGER)
    private Set<User> users;
    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
    //endregion

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Role = {name:" + name + ", users:" + users + "}";
	}
  	
}
