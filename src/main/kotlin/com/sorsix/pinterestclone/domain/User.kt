package com.sorsix.pinterestclone.domain

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.*

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
data class User(

    @Id
    private val username: String,

    private val password: String,

    )