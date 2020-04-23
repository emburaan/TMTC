package com.tmtc.datasource.auth1

data class LoginModel(
    val createdBy: Int,
    val createdOn: String,
    val isActive: Boolean,
    val isFirstLogin: Boolean,
    val modifiedBy: Any,
    val modifiedOn: Any,
    val userId: Int,
    val userName: String,
    val message: String
)