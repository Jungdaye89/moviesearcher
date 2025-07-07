package com.moviesearcher.exception

class AuthException(val errorCode: ErrorCode) : RuntimeException(errorCode.message) 