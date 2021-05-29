package com.sorsix.pinterestclone.repository

import com.sorsix.pinterestclone.domain.Pin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PinJpaRepository : JpaRepository<Pin, Long> {

    fun findAllByCreatedById(userId: Int): List<Pin>
}