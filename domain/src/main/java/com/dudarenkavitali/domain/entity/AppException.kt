package com.dudarenkavitali.domain.entity

import java.lang.Exception

data class AppException (
    val errorType: AppErrorType) : Exception(), DomainEntity