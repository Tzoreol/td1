package fr.tzoreol.javaee.td1.dto;

import fr.tzoreol.javaee.td1.utils.Properties;

public class User extends Anyone {
    public User() {
        this.role = Properties.USER;
    }
}
