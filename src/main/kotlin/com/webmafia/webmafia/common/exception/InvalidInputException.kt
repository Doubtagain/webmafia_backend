package com.webmafia.webmafia.common.exception

class InvalidInputException (
    val fieldName: String = "",
    message: String? = "Invalid input",
) : RuntimeException(message)