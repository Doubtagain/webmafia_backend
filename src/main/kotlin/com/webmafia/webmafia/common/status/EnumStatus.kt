package com.webmafia.webmafia.common.status

enum class ResultCode (val msg: String) {
    SUCCESS("Process Succeed."),
    ERROR("Process Failed."),
}

enum class ROLE {
    MEMBER, ADMIN
}

enum class MafiaROLE {
    CIV, MAFIA
}