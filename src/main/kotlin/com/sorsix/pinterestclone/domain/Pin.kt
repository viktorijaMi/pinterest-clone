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
data class Pin(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    val url: String,

    val description: String,

    val favorites: Int,

    @ManyToOne
    private val user: User,

    )