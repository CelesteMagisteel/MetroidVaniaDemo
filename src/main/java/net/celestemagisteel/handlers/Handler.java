package net.celestemagisteel.handlers;

import net.celestemagisteel.events.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Handler<T extends Event> {

    private final List<EventListenerStorage> calls = new ArrayList<>();
    private final Class<T> event;

    public Handler(Class<T> event) {
        this.event = event;
    }

    public Class<T> getEventClass() {
        return event;
    }

    public void registerEvents(Listener listener) {
        calls.addAll(getEventListeners(listener));
        Collections.sort(calls);
    }

    public void raiseEvent(T raisedEvent) throws InvocationTargetException, IllegalAccessException {
        for (EventListenerStorage eventListenerStorage : calls) {
            eventListenerStorage.getListenerMethod().invoke(eventListenerStorage.getListener(), raisedEvent);
            if (raisedEvent.isCancelled()) break;
        }
        raisedEvent.finalProcessing();
    }

    private List<EventListenerStorage> getEventListeners(Listener listener) {
        final List<EventListenerStorage> methods = new ArrayList<>();

        for (final Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventListener.class)) {
                if (method.getParameterTypes()[0] == event) {
                    methods.add(new EventListenerStorage(method.getAnnotation(EventListener.class).priority(), method, listener));
                }
            }
        }
        return methods;
    }

}
