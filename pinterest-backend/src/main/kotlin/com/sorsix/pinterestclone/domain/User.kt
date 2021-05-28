package com.sorsix.pinterestclone.domain

import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @Column(length = 1000)
    var username: String,

)  {

}