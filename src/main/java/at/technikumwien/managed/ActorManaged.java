package at.technikumwien.managed;

import java.util.List;
import at.technikumwien.entity.Actor;
import at.technikumwien.services.ActorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Flo on 21/10/2016.
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