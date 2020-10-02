package net.celestemagisteel.handlers;

import java.lang.reflect.Method;

public class EventListenerStorage implements Comparable<EventListenerStorage> {

    private final ListenerPriority priority;
    private final Method listenerMethod;
    private final Listener listener;

    public EventListenerStorage(ListenerPriority priority, Method method, Listener listener) {
        this.priority = priority;
        this.listenerMethod = method;
        this.listener = listener;
    }

    public ListenerPriority getPriority() {
        return priority;
    }

    public Method getListenerMethod() {
        return listenerMethod;
    }

    public Listener getListener() {
        return listener;
    }

    @Override
    public int compareTo(EventListenerStorage o) {
        return this.priority.compareTo(o.priority);
    }
}
