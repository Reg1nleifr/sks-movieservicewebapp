package at.technikumwien.managedbeans;

import at.technikumwien.entity.Studio;
import at.technikumwien.services.facades.StudioService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Flo & Stefan
 * Managed Bean Studio
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
