package com.webmafia.webmafia.member.service

import com.webmafia.webmafia.common.authority.JwtTokenProvider
import com.webmafia.webmafia.common.authority.TokenInfo
import com.webmafia.webmafia.common.exception.InvalidInputException
import com.webmafia.webmafia.common.status.ROLE
import com.webmafia.webmafia.member.dto.LoginDto
import com.webmafia.webmafia.member.dto.MemberDtoRequest
import com.webmafia.webmafia.member.dto.MemberDtoResponse
import com.webmafia.webmafia.member.entity.Member
import com.webmafia.webmafia.member.entity.MemberRole
import com.webmafia.webmafia.member.repository.MemberRepository
import com.webmafia.webmafia.member.repository.MemberRoleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder
) {
    /**
     * Sign Up
     */

    fun signUp(memberDtoRequest: MemberDtoRequest):String{
        var member: Member? = memberRepository.findByLoginId(memberDtoRequest.loginId)
        if (member != null){
            throw InvalidInputException("loginId", "Already Register ID")
        }

        member = memberDtoRequest.toEntity()
        memberRepository.save(member)

        val memberRole: MemberRole = MemberRole(null, ROLE.MEMBER, member)
        memberRoleRepository.save(memberRole)

        return "Success Sign Up";
    }

    /**
     * Login -> Generate Token
     */
    fun login(loginDto: LoginDto): TokenInfo {
        val member = memberRepository.findByLoginId(loginDto.loginId)?: throw InvalidInputException("ID or PWD is wrong.")
        val encoder = SCryptPasswordEncoder(16, 8, 1, 8, 8)
        if(!encoder.matches(loginDto.password, member.password)){
            throw InvalidInputException("Password does not match ID or PWD")
        }

        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.loginId, member.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)

        return jwtTokenProvider.createToken(authentication)
    }

    /**
     * Search my Info
     */
    fun searchMyInfo(id: Long): Member {
        val member = memberRepository.findByIdOrNull(id) ?: throw InvalidInputException("id", "Not (${id}) User Found")
        return member
    }

    /**
     * Edit My Info
     */
    fun saveMyInfo(memberDtoRequest: MemberDtoRequest): String {
        val member: Member = memberDtoRequest.toEntity()
        memberRepository.save(member)
        return "Edit Complete"
    }
}