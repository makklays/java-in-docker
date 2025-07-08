package com.techmatrix18.listeners;

import com.techmatrix18.events.UserAutobattledEvent;
import com.techmatrix18.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEmailNotificationListener {

    @EventListener
    public void onUserRegistered(UserRegisteredEvent event) {
        // Reacción a evento
        System.out.println("Send email to: " + event.getEmail());
    }

    @EventListener
    public void onUserAutobattled(UserAutobattledEvent event) {
        // Reaction on event
        System.out.println("Send email about autobattle '" + event.getAutobattle().getTitle() + "' to: " + event.getEmail());
    }
}

