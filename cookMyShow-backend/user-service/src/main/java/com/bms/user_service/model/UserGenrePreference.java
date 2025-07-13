package com.bms.user_service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_genre_preferences")
public class UserGenrePreference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long genreId;

    public UserGenrePreference() {}

    public UserGenrePreference(Long userId, Long genreId) {
        this.userId = userId;
        this.genreId = genreId;
    }
}
