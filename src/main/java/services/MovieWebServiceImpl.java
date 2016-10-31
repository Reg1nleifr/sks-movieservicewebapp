package services;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entity.Movie;
import helpers.MovieRootElement;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService(endpointInterface = "services.MovieWebService",
            serviceName = "MovieService",
            portName = "MovieServicePort")
public class MovieWebServiceImpl implements MovieWebService {

    @Inject
    private MovieService movieService;

    @WebMethod
    public List<Movie> getAllMovies() {
        return movieService.getAll();
    }

    @WebMethod
    public List<Movie> getMoviesContains(String name)
    {
        return movieService.contains(name);
    }

    @WebMethod
    public String persistMoviesFromSoap(String soapInput) {
        int start = soapInput.indexOf("Body>") + 5;
        Matcher m = Pattern.compile("</[^>]*Body").matcher(soapInput);
        if(!m.find()) {
            return "Document not valid";
        }

        int end = m.start();
        String output = soapInput.substring(start, end);
        Unmarshaller unmarshaller = null;
        try {
            InputStream soapInutAsByteStream = new ByteArrayInputStream(soapInput.getBytes());
            MovieRootElement movieRootElement = getMovieRootElement(new StreamSource(soapInutAsByteStream));
            persistMoviesFromList(movieRootElement.getMovies());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "200 Ok";
    }

    @WebMethod
    public String persistMoviesFromFile(String fileName) {
        try {
            MovieRootElement movieRootElement= getMovieRootElement(new StreamSource(
                    new File(fileName)));
            persistMoviesFromList(movieRootElement.getMovies());
        } catch (JAXBException e) {
            return "400 Internal Server Error";
        }

        return "200 Ok";
    }

    private MovieRootElement getMovieRootElement(Source source) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(MovieRootElement.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<MovieRootElement> jaxbElement = unmarshaller.unmarshal(source, MovieRootElement.class);
        return jaxbElement.getValue();
    }

    private void persistMoviesFromList(List<Movie> movies) {
        movies.forEach(movie -> movieService.persist(movie));
    }

}