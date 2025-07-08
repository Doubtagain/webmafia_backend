package com.webmafia.webmafia.mafia.controller

import com.webmafia.webmafia.common.dto.BaseResponse
import com.webmafia.webmafia.common.dto.CustomUser
import com.webmafia.webmafia.mafia.dto.MafiaSessionDtoRequest
import com.webmafia.webmafia.mafia.entity.Msg
import com.webmafia.webmafia.mafia.entity.Session
import com.webmafia.webmafia.mafia.service.MafiaServices
import com.webmafia.webmafia.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/game/mafia")
@RestController
class MafiaControllers(private val memberService: MemberService, private val mafiaServices: MafiaServices) {

    /**
     * Create Session
     */
    @PostMapping("/session")
    fun createSession(@RequestBody @Valid sessionDtoRequest: MafiaSessionDtoRequest): BaseResponse<Unit> {
        val userId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).userId
        val response = memberService.searchMyInfo(userId)

        sessionDtoRequest.hostName = response

        val resultMsg: String = mafiaServices.createSession(sessionDtoRequest)

        return BaseResponse(msg = resultMsg)
    }

    @GetMapping("/session")
    fun getSession(@RequestParam userId: Long): BaseResponse<Session> {
        TODO("Not EZ")
    }
}