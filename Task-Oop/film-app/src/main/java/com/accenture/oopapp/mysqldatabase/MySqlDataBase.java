package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Genre;
import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.MovieType;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.frontend.FilmApp;
import com.accenture.oopapp.users.Gender;
import com.accenture.oopapp.users.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class MySqlDataBase implements DataBase
{

    private static DataBase instance = null;

    private MySqlDataBase()
    {
    DB_DRIVER = "com.mysql.jdbc.Driver";
    DB_CONNECTION = "jdbc:mysql://127.0.0.1:3306/filmappdb?autoReconnect=true&useSSL=false";
    DB_USER = "root";
    DB_PASSWORD = "";
    getDBConnection();
    }

    public static DataBase getInstance()
    {
        if(instance == null)
        {
            synchronized (MySqlDataBase.class)
            {
                if(instance == null)
                {
                    instance = new MySqlDataBase();
                }
            }
        }
        return instance;
    }

   private final String DB_DRIVER;
   private final String DB_CONNECTION;
   private final String DB_USER;
   private final String DB_PASSWORD;

    private Connection dbConnection = null;
    private boolean getDBConnection()
    {
        try
        {
            Class.forName(DB_DRIVER);
        }
        catch (ClassNotFoundException e)
        {
            System.err.println(e.getMessage());
        }
        try
        {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
            return true;
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return false;
    }

    @Override
    public List<Movie> getMovieList()
    {
        List<Movie> movieList = new ArrayList<>(10);
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM movie");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                String[] genresString  = rs.getString("genres").split(",");
                Set<Genre> genres = new HashSet<>();
                for (String s : genresString)
                {
                    genres.add(Genre.valueOf(s));
                }
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(genres),rs.getString("releaseDate"),rs.getString("description"),rs.getDouble("rating")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return  movieList;
    }

    @Override
    public boolean isUserExist(String nickName)
    {
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM user WHERE nickName =?");
            stmt.setString(1,nickName);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next())
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean isConnect(String nickName,String password)
    {
        try {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM user WHERE nickName =? AND passWord=?");
            stmt.setString(1, nickName);
            stmt.setString(2, password);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next())
            {
                FilmApp.session.setCurrentUser(new User(resultSet.getString("name"), resultSet.getInt("age"), Gender.valueOf(resultSet.getString("gender")),
                        resultSet.getString("nickName"), resultSet.getString("passWord"), resultSet.getString("isAdmin").equals("true")));
                return true;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addUserToDataBase(User user)
    {
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, 'false')");
            stmt.setString(1,user.getName());stmt.setInt(2,user.getAge());
            stmt.setString(3,user.getGender().toString());
            stmt.setString(4,user.getNickName());stmt.setString(5,user.getPassWord());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //Тут можно сделать фильтер поиска и текст и все это в одном методе
    @Override
    public List<Movie> idSearch(String text)
    {
        List<Movie> movieList = new ArrayList<>();
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM movie WHERE movieId LIKE ?");
            stmt.setString(1,"%"+text+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                String[] genresString  = rs.getString("genres").split(",");
                Set<Genre> genres = new HashSet<>();
                for (String s : genresString)
                {
                    genres.add(Genre.valueOf(s));
                }
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(genres),rs.getString("releaseDate"),rs.getString("description"),rs.getDouble("rating")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public List<Movie> nameSearch(String text)
    {
        List<Movie> movieList = new ArrayList<>();
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM movie WHERE movieName LIKE ?");
            stmt.setString(1,"%"+text+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                String[] genresString  = rs.getString("genres").split(",");
                Set<Genre> genres = new HashSet<>();
                for (String s : genresString)
                {
                    genres.add(Genre.valueOf(s));
                }
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(genres),rs.getString("releaseDate"),rs.getString("description"),rs.getDouble("rating")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public List<Movie> dataSearch(String text)
    {
        List<Movie> movieList = new ArrayList<>();
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM movie WHERE releaseDate LIKE ?");
            stmt.setString(1,"%"+text+"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                String[] genresString  = rs.getString("genres").split(",");
                Set<Genre> genres = new HashSet<>();
                for (String s : genresString)
                {
                    genres.add(Genre.valueOf(s));
                }
                movieList.add(new Movie(rs.getString("movieId"),rs.getString("movieName"), MovieType.valueOf(rs.getString("movieType")),EnumSet.copyOf(genres),rs.getString("releaseDate"),rs.getString("description"),rs.getDouble("rating")));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return movieList;
    }

    @Override
    public List<Review> getFilmsReview(Movie movie)
    {
        List<Review> reviewList = new ArrayList<>();
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM review WHERE movieId=?");
            stmt.setString(1,movie.getMovieId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
             reviewList.add(new Review(rs.getInt("reviewId"),rs.getString("text"),rs.getString("postData"),rs.getString("userId"),rs.getDouble("userRating")));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return reviewList;
    }

    @Override
    public boolean removeReview(Review review)
    {
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("DELETE FROM review WHERE reviewId=?");
            stmt.setInt(1,review.getReviewId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void addReview(Movie movie,User user, String text, double rating)
    {
        try
        {
            PreparedStatement stmt =dbConnection.prepareStatement("INSERT INTO review (`movieId`, `text`, `postData`, `userId`, `userRating`) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1,movie.getMovieId());stmt.setString(2,text);stmt.setString(3,new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
            stmt.setString(4,user.getNickName());stmt.setDouble(5,rating);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void editingReview(Review review, String text) {
        try
        {
            PreparedStatement stmt = dbConnection.prepareStatement("UPDATE review SET text =? WHERE reviewId =?");
            stmt.setString(1,text);stmt.setInt(2,review.getReviewId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void recalculateFilmRating(Movie movie)
    {
        try
        {
            double rating = 0;
            int count = 0;
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT userRating FROM review WHERE movieId =?");
            stmt.setString(1,movie.getMovieId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                rating +=rs.getDouble("userRating");
                count++;
            }
            rating =  rating/count;
            PreparedStatement stmtUpDate = dbConnection.prepareStatement("UPDATE movie SET rating =? WHERE movieId =?");
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
        PreparedStatement stmt = dbConnection.prepareStatement("INSERT INTO movie VALUES (?, ?, ?, ?, ?, ?, ?)");
        stmt.setString(1,movie.getMovieId());stmt.setString(2,movie.getMovieName());stmt.setString(3,movie.getMovieType().name());
        String genres ="";
        for (var item:movie.getGenres())
        {
              genres +=item.name()+",";
        }
        genres = new StringBuilder(genres).deleteCharAt(genres.length()-1).toString();
        stmt.setString(4,genres);stmt.setString(5,movie.getReleaseDate());
        stmt.setDouble(6,movie.getRating());stmt.setString(7,movie.getDescription());
        stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}