package com.bms.user_service.repository;

import com.bms.user_service.model.UserGenrePreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserGenrePreferenceRepository extends JpaRepository<UserGenrePreference,Long> {
   @Query(value = "SELECT u.genre_id from user_genre_preferences u where u.user_id=:userId",nativeQuery = true)
    List<Long> findGenreListByUserId(@Param("userId") Long userId);

    void deleteByUserId(Long id);
}
