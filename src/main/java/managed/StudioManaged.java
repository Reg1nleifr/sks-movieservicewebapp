package managed;

import entity.Studio;
import services.StudioFacade;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Stefan on 26.10.2016.
 */
@RequestScoped
@Named("reqStudio")
public class StudioManaged {
    @Inject
    StudioFacade studioService;

    public List<Studio> getAllStudios() {
        return studioService.getAll();
    }
}
