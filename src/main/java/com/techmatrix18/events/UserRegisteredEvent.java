package com.techmatrix18.events;

/**
 * An event representing a user that registered in the system.
 *
 * By this event - sending an email to user
 * EDA - Event-Driven Architecture
 *
 * @author Alexander Kuziv - makklays@gmail.com
 * @since 08-07-2025
 * @version 0.0.1
 */

public class UserRegisteredEvent {
    private final String email;

    public UserRegisteredEvent(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
}

