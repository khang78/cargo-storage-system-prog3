package event;

import geschaeftslogik.Manager;

public class SaveJBPListener implements EventListener<SaveJBPEvent> {
    Manager manager;
    public SaveJBPListener(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void onEvent(SaveJBPEvent event) {
        manager.saveJBP();
    }
}
