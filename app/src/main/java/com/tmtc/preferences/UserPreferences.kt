package com.dpoints.dpointsmerchant.preferences

import android.content.Context
import androidx.core.content.edit
import com.dpoints.dpointsmerchant.utilities.fromJson
import com.dpoints.dpointsmerchant.utilities.toJson
import com.tmtc.datasource.auth1.DataModel
import com.tmtc.datasource.auth1.RoleModel


class UserPreferences {

    companion object {
        val instance: UserPreferences by lazy { UserPreferences() }
        private const val USER = "USER"
        private const val ROLE = "ROLE"
        private const val IS_LOGGED_IN = "IS_LOGGED_IN"
        private const val PREFERENCE_NAME = "user.preferences"
        private const val SAVED_TOKEN = "TOKEN"
        private const val REFRESH_TOKEN = "REFRESHTOKEN"
        private const val CHANCE_ID = "CHANCEID"
    }

    fun saveUser(context: Context, user: DataModel) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit {
            putString(USER, user.toJson())
            putBoolean(IS_LOGGED_IN, true)
            apply()
        }
    }

    fun saveRole(context: Context,roleModel: RoleModel) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit {
            putString(ROLE, roleModel.toJson())
            apply()

        }
    }


    fun logout(context: Context) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit().clear().commit()
    }

    fun getUser(context: Context): DataModel? {
        val userStr = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(USER, null)
        return userStr?.fromJson()
    }

    fun getRole(context: Context):RoleModel?{
        val roleStr = context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
            .getString(ROLE,null)
        return roleStr?.fromJson()
    }


    fun isLoggedIn(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return preferences.getBoolean(IS_LOGGED_IN, false)
    }

    fun clear(context: Context) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit { clear() }
    }


    fun setLoggedIn(context: Context) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit {
            putBoolean(IS_LOGGED_IN, true)
            apply()
        }
    }

    fun saveToken(context: Context, token: String?) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit() {
            putString(SAVED_TOKEN, token)
            apply()
        }
    }

    fun getTokken(context: Context): String? {
        val tokenn = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(SAVED_TOKEN, null)
        return tokenn
    }

    fun saveRefreshToken(context: Context, token: String?) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit() {
            putString(REFRESH_TOKEN, token)
            apply()
        }
    }

    fun getRefreshTokken(context: Context): String? {
        val tokenn = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(REFRESH_TOKEN, null)
        return tokenn
    }

    fun saveChanceId(context: Context, id: String?) {
        val preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        preferences.edit() {
            putString(CHANCE_ID, id)
            apply()
        }
    }

    fun getChanceId(context: Context): String? {
        val id = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
            .getString(CHANCE_ID, null)
        return id

    }
}