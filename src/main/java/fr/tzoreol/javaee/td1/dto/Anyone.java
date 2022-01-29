package fr.tzoreol.javaee.td1.dto;

import fr.tzoreol.javaee.td1.interfaces.AnyoneInterface;

public abstract class Anyone implements AnyoneInterface {
    protected String role;

    @Override
    public String introduceYourself() {
        return "Hello, I'm " + role;
    }
}
