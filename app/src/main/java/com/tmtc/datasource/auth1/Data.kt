package com.tmtc.datasource.auth1

data class Data(
    val createdBy: Int,
    val createdOn: Long,
    val currAddress: String,
    val dob: Long,
    val isActive: Boolean,
    val isFirstLogin: Boolean,
    val modifiedBy: Any,
    val modifiedOn: Any,
    val password: String,
    val permAddress: String,
    val phoneNo: String,
    val role: Int,
    val userId: Int,
    val userName: String
)