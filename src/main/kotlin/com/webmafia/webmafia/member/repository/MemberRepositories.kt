package com.webmafia.webmafia.member.repository

import com.webmafia.webmafia.member.entity.Member
import com.webmafia.webmafia.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long>{
    fun findByLoginId(loginId: String): Member?
}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>