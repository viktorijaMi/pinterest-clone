package com.sorsix.pinterestclone.repository

import com.sorsix.pinterestclone.domain.Pin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PinJpaRepository : JpaRepository<Pin, Long> {

    @Modifying
    @Query("update Pin p set p.description = :description where p.id = :id")
    fun updatePin(id: Long, description: String): Pin

    @Modifying
    @Query("update Pin p set p.favorites = p.favorites + 1 where p.id = :id")
    fun updateFavorites(id: Long)

    fun findAllByUserUsername(username : String) : List<Pin>

}