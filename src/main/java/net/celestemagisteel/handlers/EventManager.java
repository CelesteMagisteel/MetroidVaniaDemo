package net.celestemagisteel.handlers;

import net.celestemagisteel.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class EventManager {

    private static final Map<Class<? extends Event>, Handler> handlers = new HashMap<>();

    public static void registerHandler(Handler handler) {
        handlers.put(handler.getEventClass(), handler);
    }

    public static void registerEvents(Listener listener) {
        for (Class<? extends Event> event : handlers.keySet()) {
            System.out.println("Test");
            handlers.get(event).registerEvents(listener);
        }
    }

    public static void raiseEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        handlers.get(event.getClass()).raiseEvent(event);
    }
}
