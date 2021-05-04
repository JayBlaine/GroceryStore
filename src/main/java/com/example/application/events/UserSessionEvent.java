package com.example.application.events;

import com.example.application.Account;
public class UserSessionEvent {
	private Account user = null;

    /**
     * Default constructor. Remember to set the target user.
     */
    public UserSessionEvent() {

    }

    /**
     * Alternative constructor which takes as parameter the target user.
     * 
     * @param user
     */
    public UserSessionEvent(Account user) {
        this.setUser(user);
    }

    /**
     * Sets the user who is the target of this event
     * 
     * @param user
     */
    public void setUser(Account user) {
        this.user = user;
    }

    /**
     * Returns the target user of this event
     * 
     * @return
     */
    public Account getUser() {
        return user;
    }

}
