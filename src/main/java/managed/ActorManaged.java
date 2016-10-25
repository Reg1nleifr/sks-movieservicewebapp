package managed;

import java.util.List;
import entity.Actor;
import services.ActorFacade;

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
    ActorFacade actorService;

    public List<Actor> getAllActors() {
        return actorService.getAll();
    }
}
