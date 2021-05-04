package com.example.application.events;

import com.example.application.Item;





public class CartUpdatedEvent {
	public enum EventType {
        ITEM_ADDED, ITEM_REMOVED, ITEM_QUANTITY_CHANGED;
    };

    private final EventType type;

    private final Item item;

    public CartUpdatedEvent(EventType type, Item item) {
        this.type = type;
        this.item = item;
    }
    /**
     * Get the event type which defines which action took place in the cart.
     * 
     * @return
     */
    public EventType getType() {
        return type;
    }

    /**
     * Returns the product which was the subject of the event.
     * 
     * @return
     */
    public Item getProduct() {
        return item;
    }

    /**
     * Cart update listener
     * 
     * 
     * 
     */
    public interface CartUpdateListener {
        /**
         * The shopping cart has been updated.
         * 
         * @param event
         */
        public void cartUpdated(CartUpdatedEvent event);
    }

}
