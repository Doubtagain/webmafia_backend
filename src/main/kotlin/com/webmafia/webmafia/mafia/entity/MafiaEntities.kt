package com.webmafia.webmafia.mafia.entity

import com.webmafia.webmafia.common.status.MafiaROLE
import com.webmafia.webmafia.member.entity.Member
import jakarta.persistence.*

@Entity
class Msg(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 30, updatable = false)
    var sessionId: String,

    @Column(nullable = false, length = 30)
    var uploadNickname: String,

    @Column(nullable = false, length = 3000)
    var message: String,

    @Column(nullable = false, length = 10)
    var dying: Boolean? = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    var memberRole: MafiaMemberRole? = null
)

@Entity
class MafiaMemberRole(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    var mafiaRole: MafiaROLE,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = ForeignKey(name = "fk_mafia_member_role_member_id_msg"))
    var member: Member,

) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "memberRole")
    var msg: MutableList<Msg>? = null
}


class Session(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    var hostMember: Member,

    @Column(nullable = false, length = 30)
    var sessionName: String,

    @Column(nullable = false, length = 8)
    var sessionLimit: Int,

    @Column(nullable = false, length = 8)
    var sessionStart: Boolean? = false,

    @Column(nullable = false, length = 8)
    var sessionLock: Boolean? = false,

    @Column(nullable = true, length = 8)
    var sessionPWD: String,
)