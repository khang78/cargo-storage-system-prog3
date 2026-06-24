package event;

import geschaeftslogik.Manager;

public class SaveJOSListener implements EventListener<SaveJOSEvent> {
    Manager manager;
    public SaveJOSListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void onEvent(SaveJOSEvent event) {
        manager.saveJOS();
    }
}
