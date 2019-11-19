package com.accenture.oopapp.businesslayer.main.interfaces;

import com.accenture.oopapp.businesslayer.exceptionhandler.InputDataException;

public interface ReviewBusinessLayer
{
    void addReview(String movieId,String userId,String txt,double rating)throws InputDataException;
}
