package com.anoop.expmanager.model;
// Generated 9 Feb, 2016 6:00:21 PM by Hibernate Tools 4.0.0

/*import com.fasterxml.jackson.annotation.JsonIgnore;*/

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles")
public class Roles implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Integer id;
    private String rolename;
    /*private Set<User> users = new HashSet<User>(0);*/
    /*  private Set<Menu> menuSet = new HashSet<Menu>(0);
    */

    public Roles() {
    }

    public Roles(String rolename) {
        this.rolename = rolename;
    }

    public Roles(String rolename, Set<User> users, Set<Menu> menuSet) {
        this.rolename = rolename;
//		this.users = users;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "rolename", nullable = false, length = 45)
    public String getRolename() {
        return this.rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "roles")
     public Set<User> getUsers() {
         return this.users;
     }

     public void setUsers(Set<User> users) {
         this.users = users;
     }*/

    /*///@JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }*/
}
