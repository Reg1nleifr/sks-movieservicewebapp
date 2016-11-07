package at.technikumwien.services.soap;

import at.technikumwien.entity.Movie;
import at.technikumwien.services.facades.MovieService;
import at.technikumwien.services.helpers.MovieRootElement;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService(endpointInterface = "at.technikumwien.services.soap.MovieWebService",
        serviceName = "MovieService",
        portName = "MovieServicePort")
public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService movieService;


    @Override
    @WebMethod
    public MovieRootElement getAllMovies() {
        return new MovieRootElement(movieService.getAll());
    }


    @Override
    @WebMethod
    public MovieRootElement getMoviesContains(String name)
    {
        return new MovieRootElement(movieService.contains(name));
    }

    @Override
    @WebMethod
    public void persistMoviesFromSoap(String soapInput) throws JAXBException {
        InputStream soapInputAsByteStream = new ByteArrayInputStream(soapInput.getBytes());
        MovieRootElement movieRootElement = getMovieRootElement(new StreamSource(soapInputAsByteStream));
        persistMovies(movieRootElement.getMovies());
    }

    @Override
    public void persistMovies(List<Movie> movies) {
        movieService.persistTransactionally(movies);
    }

    private MovieRootElement getMovieRootElement(Source source) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(MovieRootElement.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<MovieRootElement> jaxbElement = unmarshaller.unmarshal(source, MovieRootElement.class);
        return jaxbElement.getValue();
    }
}