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
    private val id: Long,

    private val url: String,

    private val description: String,

    private val favorites: Int,

    @ManyToOne
    private val user: User,

    )