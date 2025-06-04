package com.webmafia.webmafia.common.dto

import com.webmafia.webmafia.common.status.ResultCode

data class BaseResponse<T>(
    val resultCode: String = ResultCode.SUCCESS.name,
    val data: T? = null,
    val msg: String = ResultCode.SUCCESS.msg,
)