package com.accenture.oopapp.mysqldatabase;

import com.accenture.oopapp.films.Movie;
import com.accenture.oopapp.films.Review;
import com.accenture.oopapp.mysqldatabase.interfaces.ReviewOperation;
import com.accenture.oopapp.users.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewTable implements ReviewOperation
{
    private static ReviewTable instance = null;
    private static ConnectToDB connectToDB = ConnectToDB.getInstance();
    private Connection dbConnection;
    private ReviewTable(){dbConnection = connectToDB.getDbConnection();}
    public static ReviewOperation getInstance()
    {
        if(instance == null)
        {
            synchronized (ReviewTable.class)
            {
                if(instance == null)
                {
                    instance = new ReviewTable();
                }
            }
        }
        return instance;
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
    public void addReview(Movie movie, User user, String text, double rating)
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
}
