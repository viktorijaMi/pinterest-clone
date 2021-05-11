package com.sorsix.pinterestclone.repository

import com.sorsix.pinterestclone.domain.Pin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface PinJpaRepository : JpaRepository<Pin, Long> {

    @Transactional
    @Modifying
    @Query("update Pin p set p.description = :description where p.id = :id")
    fun updatePin(id: Long, description: String)

    fun findAllByUserUsername(username: String): List<Pin>

}