package soap;

import xml.helpers.MovieRootElement;

import javax.jws.WebService;

/**
 * Created by Flo on 28/10/2016.
 */
@WebService
interface MovieWebService {

    MovieRootElement getAllMovies();
    MovieRootElement getMoviesContains(String name);
    String persistMoviesFromSoap(String soapInput);

}