package com.webmafia.webmafia.member.controller

import com.webmafia.webmafia.common.authority.TokenInfo
import com.webmafia.webmafia.common.dto.BaseResponse
import com.webmafia.webmafia.member.dto.LoginDto
import com.webmafia.webmafia.member.dto.MemberDtoRequest
import com.webmafia.webmafia.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/api/member")
@RestController
class MemberController (private val memberService: MemberService) {
    /**
     * Sign Up
     */
    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid memberDtoRequest: MemberDtoRequest): BaseResponse<Unit> {
        val resultMsg: String = memberService.signUp(memberDtoRequest)
        return BaseResponse(msg = resultMsg)
    }

    /**
     * Login
     */
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginDto: LoginDto): BaseResponse<TokenInfo> {
        val tokenInfo = memberService.login(loginDto)
        return BaseResponse(data = tokenInfo)
    }
}