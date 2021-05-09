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
    var id: Long,

    var url: String,

    var description: String,

    var favorites: Int,

    @ManyToOne
    private var user: User,

    )