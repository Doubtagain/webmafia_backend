package com.webmafia.webmafia.member.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.webmafia.webmafia.member.entity.Member
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder

data class MemberDtoRequest (
    val id: Long?,

    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,

    @field:NotBlank
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#\$%^&*])[a-zA-Z0-9!@#\$%^&*]{8,20}\$",
        message = "Involve Alphabet, Number, Special Char and limited 8~20 char"
    )
    @JsonProperty("password")
    private val _password: String?,

    @field:NotBlank
    @JsonProperty("name")
    private val _name: String?,

    @field:NotBlank
    @JsonProperty("nickname")
    private val _nickName: String?,

    @field:NotBlank
    @field:Email
    @JsonProperty("email")
    private val _email: String?,
) {

    private val encoder = SCryptPasswordEncoder(16, 8, 1, 8, 8)

    val loginId: String
        get() = _loginId!!
    private val password: String
        get() = encoder.encode(_password)
    val name: String
        get() = _name!!
    val nickName: String
        get() = _nickName!!
    val email : String
        get() = _email!!

    fun toEntity(): Member =
        Member(id, loginId, password, name, nickName, email)
}

data class LoginDto(
    @field:NotBlank
    @JsonProperty("loginId")
    private val _loginId: String?,

    @field:NotBlank
    @JsonProperty("password")
    private val _password: String?,
) {
    val loginId: String
        get() = _loginId!!
    val password: String
        get() = _password!!
}

data class MemberDtoResponse (
    val id: Long,
    val loginId: String,
    val name: String,
    val nickName: String,
    val email: String,
)