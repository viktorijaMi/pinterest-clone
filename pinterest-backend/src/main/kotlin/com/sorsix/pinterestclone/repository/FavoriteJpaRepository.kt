package com.sorsix.pinterestclone.repository

import com.sorsix.pinterestclone.domain.Favorite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface FavoriteJpaRepository : JpaRepository<Favorite, Long> {

    fun findAllByPinId(id: Long) : List<Favorite>

    fun findByPinId(pinId: Long) : Optional<Favorite>

    @Transactional
    @Modifying
    @Query("update Favorite f set f.numFavorites = f.numFavorites+1 where f.id = :id")
    fun increaseFavorites(@Param("id") id: Long)

    @Transactional
    @Modifying
    @Query("update Favorite f set f.numFavorites = f.numFavorites-1 where f.id = :id")
    fun decreaseFavorites(@Param("id") id: Long)
}