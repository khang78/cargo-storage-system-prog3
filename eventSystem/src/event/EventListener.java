package event;

import java.util.EventObject;

public interface EventListener<T extends EventObject> {
    void onEvent(T event);
}