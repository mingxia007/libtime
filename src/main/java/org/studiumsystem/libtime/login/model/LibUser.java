package org.studiumsystem.libtime.login.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
public class LibUser {

    @Id
    @Column("user_id")
    private Long id;//auto generate by databank
    @Column("username")
    private String username;
    @Column("password")
    private String password;

    public LibUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "username: " + username;
    }
}
