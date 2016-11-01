package helpers;

import entity.Movie;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * Created by Flo on 29/10/2016.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieRootElement {
    @XmlElementWrapper(name="movies")
    @XmlElement(name="movie")
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}
