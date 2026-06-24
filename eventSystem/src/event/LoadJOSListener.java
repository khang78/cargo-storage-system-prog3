package event;

import geschaeftslogik.Manager;
import persistence.Persistence;

public class LoadJOSListener implements EventListener<LoadJOSEvent>{
    private Manager manager;
    private Persistence persistence;
    public LoadJOSListener(Manager manager) {
        this.manager = manager;
        this.persistence = new Persistence();
    }

    @Override
    public void onEvent(LoadJOSEvent event) {
        manager.loadJOS();
    }
}
