package com.webmafia.webmafia.mafia.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.webmafia.webmafia.mafia.entity.Msg
import com.webmafia.webmafia.mafia.entity.Session
import com.webmafia.webmafia.member.entity.Member
import jakarta.validation.constraints.NotBlank

/**
 * Out-Session, Create Session
 */

data class MafiaSessionDtoRequest(
    val id: Long?,

    @field:NotBlank
    @JsonProperty("sessionName")
    private val _sessionName: String?,

    @JsonProperty("hostName")
    var hostName: Member,

    @field:NotBlank
    @JsonProperty("sessionLock")
    private var _sessionLock: Boolean?,

    @field:NotBlank
    @JsonProperty("sessionLimit")
    private var _sessionLimit: Int?,

    @JsonProperty("sessionPWD")
    private var _sessionPWD: String,
) {
    val sessionName: String
    get() = _sessionName!!
    val sessionLock: Boolean
    get() = _sessionLock!!
    val sessionLimit: Int
    get() = _sessionLimit!!
    val sessionPWD: String
    get() = _sessionPWD

    fun toSession(): Session =
        Session(hostMember = hostName, sessionName = sessionName, sessionLock = sessionLock, sessionLimit = sessionLimit, sessionStart = false, sessionPWD = sessionPWD)
}

/**
 * IN-Session, MSG Request
 */
data class MafiaMsgDtoRequest(
    val id: Long?,

    @field:NotBlank
    @JsonProperty("sessionId")
    private val _sessionId: String?,

    @JsonProperty("nickName")
    private var nickName: String,

    @field:NotBlank
    @JsonProperty("message")
    private var _message: String?,

    @JsonProperty("dying")
    private var _dying: Boolean?
) {
    val sessionId: String
        get() = _sessionId!!
    val message: String
        get() = _message!!

    fun toMsg(): Msg =
        Msg(sessionId = sessionId, message = message, uploadNickname = nickName)
}