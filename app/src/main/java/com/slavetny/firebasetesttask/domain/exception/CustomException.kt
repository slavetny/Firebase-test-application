package com.slavetny.firebasetesttask.domain.exception

sealed class CustomException {
    class LoginValidationException : Exception("Login data validation failed")
}