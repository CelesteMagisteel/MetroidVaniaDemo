package net.celestemagisteel.handlers;

import net.celestemagisteel.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Handler<T extends Event> {

    private final Map<Listener, List<Method>> calls = new HashMap<>();
    private final Class<T> event;

    public Handler(Class<T> event) {
        this.event = event;
    }

    public Class<T> getEventClass() {
        return event;
    }

    public void registerEvents(Listener listener) {
        calls.put(listener, getEventListeners(listener.getClass()));
    }

    public void raiseEvent(T raisedEvent) throws InvocationTargetException, IllegalAccessException {
        for (Listener listener : calls.keySet()) {
            for (Method method : calls.get(listener)) {
                method.invoke(listener, raisedEvent);
                if (raisedEvent.isCancelled()) break;
            }
            if (raisedEvent.isCancelled()) break;
        }
    }

    private List<Method> getEventListeners(Class<?> type) {
        final List<Method> methods = new ArrayList<>();

        for (final Method method : type.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventListener.class)) {
                if (method.getParameterTypes()[0] == event) {
                    methods.add(method);
                }
            }
        }

        return methods;
    }

}
