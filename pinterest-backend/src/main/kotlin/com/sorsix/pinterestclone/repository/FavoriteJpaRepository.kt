package com.sorsix.pinterestclone.repository

import com.sorsix.pinterestclone.domain.Favorite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FavoriteJpaRepository : JpaRepository<Favorite, Long> {

    fun findAllByPinId(id: Long): List<Favorite>

    fun findByPinId(pinId: Long): Optional<Favorite>

    fun findByPinIdAndUserId(pinId: Long, userId: Int): Optional<Favorite>
}