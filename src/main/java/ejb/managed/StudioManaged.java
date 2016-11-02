package ejb.managed;

import entity.Studio;
import services.StudioService;

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
    StudioService studioService;

    public List<Studio> getAllStudios() {
        return studioService.getAll();
    }
}
