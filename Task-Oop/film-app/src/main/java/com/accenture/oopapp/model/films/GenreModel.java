package com.accenture.oopapp.model.films;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "genre")
public class GenreModel
{
    @Id
    @Column(name = "genre_id")
    private Long genreId;
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToMany(mappedBy="genres")
    private Collection<Movie> movie;

    public GenreModel(){}

    public GenreModel(Long genreId, Genre genre)
    {
        this.genreId = genreId;
        this.genre = genre;
    }

    public Genre getGenre() { return genre; }

    public Long getGenreId() { return genreId; }

    public void setGenreId(Long genreId) { this.genreId = genreId; }

    public void setGenre(Genre genre) { this.genre = genre; }

    @Override
    public int hashCode() {
        return Objects.hash(genreId);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreModel genre = (GenreModel) o;
        return genreId.equals(genre.getGenreId());
    }
}
