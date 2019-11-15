package com.accenture.oopapp.model.films;

import javax.persistence.*;

@Entity
@Table(name = "genre")
public class GenreModel
{
    @Id
    @Column(name = "genre_id")
    private Long genreId;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    public GenreModel(){}
}
