package com.webmafia.webmafia.mafia.repository

import com.webmafia.webmafia.mafia.entity.Msg
import com.webmafia.webmafia.mafia.entity.Session
import org.springframework.data.jpa.repository.JpaRepository

interface MafiaMsgRepository : JpaRepository<Msg, Long>

interface MafiaSessionRepository : JpaRepository<Session, Long>