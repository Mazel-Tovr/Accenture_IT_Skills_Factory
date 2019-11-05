package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.model.films.Movie;
import com.accenture.oopapp.model.films.Review;
import com.accenture.oopapp.mysqldatabase.interfaces.ReviewOperation;
import com.accenture.oopapp.model.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ReviewTable implements ReviewOperation
{
    @Autowired
    private  ConnectToDB dbConnection;
    @Override
    public List<Review> getFilmsReview(Movie movie) {
        List<Review> reviewList = new ArrayList<>();
        try {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM review WHERE movieId=?");
            stmt.setString(1, movie.getMovieId());
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
    public Review getReview(String reviewId)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT * FROM review WHERE reviewId=?");
            stmt.setString(1,reviewId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return new Review(rs.getInt("reviewId"),rs.getString("text"),rs.getString("postData"),rs.getString("userId"),rs.getDouble("userRating"));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean removeReview(Review review)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("Select movieId FROM review WHERE reviewId=?");
            stmt.setInt(1,review.getReviewId());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String movieId = rs.getString("movieId");

            stmt = dbConnection.getDbConnection().prepareStatement("DELETE FROM review WHERE reviewId=?");
            stmt.setInt(1,review.getReviewId());
            stmt.executeUpdate();
            recalculateFilmRating(movieId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeReview(int reviewId)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("Select movieId FROM review WHERE reviewId=?");
            stmt.setInt(1,reviewId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String movieId = rs.getString("movieId");

            stmt = dbConnection.getDbConnection().prepareStatement("DELETE FROM review WHERE reviewId=?");
            stmt.setInt(1,reviewId);
            stmt.executeUpdate();
            recalculateFilmRating(movieId);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void addReview(Movie movie, User user, String text, double rating)
    {
        try
        {
            PreparedStatement stmt =dbConnection.getDbConnection().prepareStatement("INSERT INTO review (`movieId`, `text`, `postData`, `userId`, `userRating`) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1,movie.getMovieId());stmt.setString(2,text);stmt.setString(3,new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
            stmt.setString(4,user.getNickName());stmt.setDouble(5,rating);
            stmt.executeUpdate();
            recalculateFilmRating(movie.getMovieId());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void addReview(String movie, String user, String text, double rating)
    {
        try
        {
            PreparedStatement stmt =dbConnection.getDbConnection().prepareStatement("INSERT INTO review (`movieId`, `text`, `postData`, `userId`, `userRating`) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1,movie);stmt.setString(2,text);stmt.setString(3,new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
            stmt.setString(4,user);stmt.setDouble(5,rating);
            stmt.executeUpdate();
            recalculateFilmRating(movie);
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
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("UPDATE review SET text =? WHERE reviewId =?");
            stmt.setString(1,text);stmt.setInt(2,review.getReviewId());
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void editingReview(int reviewId, String text)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("UPDATE review SET text =? WHERE reviewId =?");
            stmt.setString(1,text);stmt.setInt(2,reviewId);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void recalculateFilmRating(String  movieId)
    {
        try
        {
            PreparedStatement stmt = dbConnection.getDbConnection().prepareStatement("SELECT AVG(userRating) FROM review WHERE movieId =?");
            stmt.setString(1,movieId);
            ResultSet rs = stmt.executeQuery();
            double rating = 0;
            while (rs.next())
            {
                rating = rs.getDouble(1);
            }
            PreparedStatement stmtUpDate = dbConnection.getDbConnection().prepareStatement("UPDATE movie SET rating =? WHERE movieId =?");
            stmtUpDate.setDouble(1,rating);
            stmtUpDate.setString(2,movieId);
            stmtUpDate.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
