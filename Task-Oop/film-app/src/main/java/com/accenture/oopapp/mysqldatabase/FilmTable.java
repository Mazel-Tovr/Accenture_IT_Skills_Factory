package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.model.films.Genre;
import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.MovieType;
import com.accenture.oopapp.mysqldatabase.interfaces.MovieOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class FilmTable implements MovieOperation
{

    @Autowired
    private ConnectToDB dbConnection;

    @Override
    public List<Movie> getMovieList()
    {
        List<Movie> movieList = new ArrayList<>(10);
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM movie");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(parseGenres(rs.getString("genres"))),rs.getString("releaseDate"),rs.getString("description"),rs.getDouble("rating")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  movieList;
    }

    @Override
    public List<Movie> search(String filter,String text)
    {
        List<Movie> movieList = new ArrayList<>();
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM movie WHERE "+filter+" LIKE ?");
            stmt.setString(1,"%"+text+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(parseGenres(rs.getString("genres"))),rs.getString("releaseDate"),rs.getString("description"),rs.getDouble("rating")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public void recalculateFilmRating(Movie movie)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT AVG(userRating) FROM review WHERE movieId =?");
            stmt.setString(1,movie.getMovieId());
            ResultSet rs = stmt.executeQuery();
            double rating = 0;
            while (rs.next())
            {
               rating = rs.getDouble(1);
            }
            PreparedStatement stmtUpDate = dbConnection.getDbConnection().prepareStatement("UPDATE movie SET rating =? WHERE movieId =?");
            stmtUpDate.setDouble(1,rating);
            stmtUpDate.setString(2,movie.getMovieId());
            stmtUpDate.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addMoveToDataBase(Movie movie)
    {
        try
        {
        PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("INSERT INTO movie VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1,movie.getMovieId());stmt.setString(2,movie.getMovieName());stmt.setString(3,movie.getMovieType().name());
        stmt.setString(4,unParseGenres(movie.getGenres()));stmt.setString(5,movie.getReleaseDate());
        stmt.setDouble(6,movie.getRating());stmt.setString(7,movie.getDescription());
        stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    private String unParseGenres(EnumSet<Genre> genre)
    {
        String genres ="";
        for (Genre item:genre)
        {
            genres +=item.name()+",";
        }
        return new StringBuilder(genres).deleteCharAt(genres.length()-1).toString();
    }

    private Set<Genre> parseGenres(String rs)
    {
        Set<Genre> genres = new HashSet<>();
        for (String s : rs.split(","))
        {
            genres.add(Genre.valueOf(s));
        }
     return genres;
    }

}