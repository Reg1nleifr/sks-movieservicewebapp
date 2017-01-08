package at.technikumwien.managedbeans;

import at.technikumwien.entity.Actor;
import at.technikumwien.services.facades.ActorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Managed Bean Actor
 */
@RequestScoped
@Named("reqActor")
public class ActorManaged {

    @Inject
    ActorService actorService;

    public List<Actor> getAllActors() {
        return actorService.getAll();
    }
}
