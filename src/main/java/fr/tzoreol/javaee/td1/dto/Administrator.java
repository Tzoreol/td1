package fr.tzoreol.javaee.td1.dto;

import fr.tzoreol.javaee.td1.utils.Properties;

public class Administrator extends Anyone {
    public Administrator() {
        this.role = Properties.ADMIN;
    }
}
