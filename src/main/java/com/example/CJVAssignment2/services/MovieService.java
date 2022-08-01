package com.example.CJVAssignment2.services;

import com.example.CJVAssignment2.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MongoRepository movieRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List <Movie> getMovies(){

        return movieRepository.findAll();
    }

    public List <Movie> getFeatured(String Featured){

        Query query = new Query();
        query.addCriteria(Criteria.where("Featured").is(Featured));

        List <Movie> movies=mongoTemplate.find(query, Movie.class);
        return  movies;
    }
    public List <Movie> getSeries(){

        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is("series"));

        List <Movie> movies=mongoTemplate.find(query, Movie.class);
        return  movies;
    }
    public List <Movie> getOnlyMovies(){

        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is("movie"));

        List <Movie> movies=mongoTemplate.find(query, Movie.class);
        return  movies;
    }
    public List <Movie> getOnlyFeaturedMovies(){

        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is("movie"));
        query.addCriteria(Criteria.where("Featured").is("yes"));
        List <Movie> movies=mongoTemplate.find(query, Movie.class);
        return  movies;
    }
    public List <Movie> getOnlyFeaturedSeries(){

        Query query = new Query();
        query.addCriteria(Criteria.where("Type").is("series"));
        query.addCriteria(Criteria.where("Featured").is("yes"));
        List <Movie> movies=mongoTemplate.find(query, Movie.class);
        return  movies;
    }

    public void insertIntoMovies(Movie movie){

        movieRepository.insert(movie);
    }

    public Optional<Movie> getMovie(String id) throws Exception {

        Optional<Movie> movie=movieRepository.findById(id);
        if(!movie.isPresent()){
            throw new Exception("Movie with id: "+id+", is not found");
        }
        return movie;
    }

    public void deleteAMovie(String id){

        
        movieRepository.deleteById(id);

    }

    public List<Movie> getByTitle(String title)

    {
        Query query = new Query();

        query.addCriteria(Criteria.where("Title" ).regex(title+".*","i"));
        List<Movie> movies = mongoTemplate.find(query, Movie.class);
        return movies;
    }

    public Movie editMovie(String id, Movie newMovie) throws Exception {

       Optional<Movie> movie =movieRepository.findById(id);

       movie.get().setBuy(newMovie.getBuy());
       movie.get().setBigPoster(newMovie.getBigPoster());
       movie.get().setFeatured(newMovie.getFeatured());
       movie.get().setDescription(newMovie.getDescription());
//       movie.get().setId(newMovie.getId());
       movie.get().setPoster(newMovie.getPoster());
       movie.get().setRent(newMovie.getRent());
       movie.get().setTitle(newMovie.getTitle());
       movie.get().setType(newMovie.getType());
       movie.get().setYear(newMovie.getYear());


        if(movie.get().getDescription().isEmpty()||movie.get().getPoster().isEmpty()||movie.get().getBigPoster().isEmpty()||
                movie.get().getTitle().isEmpty()||movie.get().getType().isEmpty()||
                movie.get().getYear().isEmpty()){
            throw new Exception("You cannot leave blank information");
        }

        Movie updatedMovie = (Movie) movieRepository.save(movie.get());
      return updatedMovie;

    }


}
