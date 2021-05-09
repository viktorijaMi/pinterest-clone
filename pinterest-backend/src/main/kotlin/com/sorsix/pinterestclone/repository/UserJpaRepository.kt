package com.sorsix.pinterestclone.repository

import com.sorsix.pinterestclone.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<User, String> {
}