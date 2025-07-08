package com.webmafia.webmafia.mafia.service

import com.webmafia.webmafia.common.exception.InvalidInputException
import com.webmafia.webmafia.mafia.dto.MafiaSessionDtoRequest
import com.webmafia.webmafia.mafia.repository.MafiaSessionRepository
import com.webmafia.webmafia.member.entity.Member
import com.webmafia.webmafia.member.repository.MemberRepository
import com.webmafia.webmafia.member.service.MemberService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MafiaServices(
    private val mafiaSessionRepository: MafiaSessionRepository,
    private val memberRepository: MemberRepository,
){
    // Management Session

    /**
     * Create Session
     */

    fun createSession(mafiaSessionDtoRequest: MafiaSessionDtoRequest) : String {
        var member: Member? = memberRepository.findByLoginId(mafiaSessionDtoRequest.hostName.loginId)

        if (member == null) {
            throw InvalidInputException("LoginID", "No Register ID, Please Login")
        }

        var session = mafiaSessionDtoRequest.toSession()
        mafiaSessionRepository.save(session)

        return "Success Create Session"

    }

    /**
     * Join Session
     */

    fun joinSession() {
        TODO("Not EZ")
    }

    /**
     * Exit Session
     */

    fun exitSession() {
        TODO("Not EZ")
    }

    // Game Service

}