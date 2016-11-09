package at.technikumwien.services.soap.helpers;

import at.technikumwien.entity.Movie;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Flo on 29/10/2016.
 */
@XmlRootElement(name = "movies")
public class MovieRootElement {
    private List<Movie> movies;

    public MovieRootElement(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieRootElement() {
    }

    @XmlElement(name="movie")
    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
