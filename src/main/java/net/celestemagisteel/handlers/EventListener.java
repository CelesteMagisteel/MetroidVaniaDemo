package net.celestemagisteel.handlers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public @Retention(RetentionPolicy.RUNTIME)
@interface EventListener {

    ListenerPriority priority() default ListenerPriority.NORMAL;
}
