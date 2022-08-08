package com.example.CJVAssignment2.controllers;


import com.example.CJVAssignment2.CustomizedResponse;
import com.example.CJVAssignment2.models.Movie;
import com.example.CJVAssignment2.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MovieController {

 @Autowired
 private MovieService service;

    @GetMapping("/movies")
    public ResponseEntity getmovies(){

        var customizedResponse = new CustomizedResponse("A list of movies: ", service.getMovies());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/featured")
    public ResponseEntity getFeatured(@RequestParam(value = "Featured") String Featured){

        var customizedResponse = new CustomizedResponse("A list of movies and series that are featured " , service.getFeatured(Featured));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/series")
    public ResponseEntity getSeries(){

        var customizedResponse = new CustomizedResponse("A list of series :" , service.getSeries());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/onlyMovies")
    public ResponseEntity getOnlyMovies(){

        var customizedResponse = new CustomizedResponse("A list of only movies :" , service.getOnlyMovies());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/onlyFeaturedMovies")
    public ResponseEntity getOnlyFeaturedMovies(){

        var customizedResponse = new CustomizedResponse("A list of only featured movies :" , service.getOnlyFeaturedMovies());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }
    @GetMapping("/onlyFeaturedSeries")
    public ResponseEntity getOnlyFeaturedSeries(){

        var customizedResponse = new CustomizedResponse("A list of only featured series :" , service.getOnlyFeaturedSeries());

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity getmovie(@PathVariable("id") String id){

        CustomizedResponse customizedResponse = null;
        try {
            customizedResponse = new CustomizedResponse("Movie with id: "+ id, Collections.singletonList(service.getMovie(id)));
        }catch (Exception e){
            customizedResponse = new CustomizedResponse(e.getMessage(),null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity deleteAMovie(@PathVariable("id") String id){


        service.deleteAMovie(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PostMapping(value="/movies", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMovie(@RequestBody Movie movie){
        service.insertIntoMovies(movie);
        return new ResponseEntity(movie, HttpStatus.OK);
    }

    @GetMapping("/movies/title")
    public ResponseEntity getByTitle(@RequestParam(value = "title" )String title)
    {
        var customizedResponse = new CustomizedResponse("A list of movies that contain the title :", service.getByTitle(title));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @PutMapping(value ="/movies/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editMovie(@PathVariable("id") String id, @RequestBody Movie movie) throws Exception {



        var customizedResponse = new CustomizedResponse("Movie with id: "+id+" has been updated successfully" , Collections.singletonList(service.editMovie(id, movie)));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);

    }

}

