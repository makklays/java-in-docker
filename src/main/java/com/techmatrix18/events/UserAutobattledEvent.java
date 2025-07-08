package com.techmatrix18.events;

import com.techmatrix18.model.Autobattle;

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

public class UserAutobattledEvent {
    private final String email;
    private final Autobattle autobattle;

    public UserAutobattledEvent(String email, Autobattle autobattle) {
        this.email = email;
        this.autobattle = autobattle;
    }

    public String getEmail(){
        return this.email;
    }

    public Autobattle getAutobattle(){
        return this.autobattle;
    }
}

