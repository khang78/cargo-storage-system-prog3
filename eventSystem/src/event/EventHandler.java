package event;

import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

public class EventHandler<T extends EventObject> {
    private List<EventListener<T>> listenerList = new LinkedList<>();

    public void add(EventListener<T> listener) {
        listenerList.add(listener);
    }

    public void handle(T event) {
        for (EventListener<T> listener : listenerList) listener.onEvent(event);
    }

}
