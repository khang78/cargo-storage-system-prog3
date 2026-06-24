package event;

import cargo.Hazard;
import geschaeftslogik.CargoObjekt;
import geschaeftslogik.Manager;

import java.util.ArrayList;
import java.util.List;

public class ListHazardListener implements EventListener<ListHazardEvent> {
    Manager manager;
    public ListHazardListener(Manager manager){
        this.manager = manager;
    }

    @Override
    public void onEvent(ListHazardEvent event) {
        List<String> list;

        if(event.isHazard()){
            list = manager.listWithHazards();
        }else{
            list = manager.listWithoutHazards();
        }

        if(list.isEmpty()){
            System.out.println("List is empty");
        }else {
            for (String hazard : list) {
                System.out.println(hazard);
            }
        }
    }
}
