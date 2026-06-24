package event;

import geschaeftslogik.Manager;
import persistence.Persistence;

public class LoadJBPListener implements EventListener<LoadJBPEvent>{
    private Manager manager;
    private Persistence persistence;
    public LoadJBPListener(Manager manager) {
        this.manager = manager;
        this.persistence = new Persistence();
    }

    @Override
    public void onEvent(LoadJBPEvent event) {
        manager.loadJBP();
    }
}
