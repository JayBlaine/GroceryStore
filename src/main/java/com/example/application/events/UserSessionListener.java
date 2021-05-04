package com.example.application.events;

public interface UserSessionListener {
    /**
     * Called when a user logs in
     * 
     * @param event
     */
    public void loginEvent(UserSessionEvent event);

    /**
     * Called when a user logs out
     * 
     * @param event
     */
    public void logoutEvent(UserSessionEvent event);
}