package com.example.surakhsit_nepal.DataStore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = "user_preferences")
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val LOGIN_STATUS = booleanPreferencesKey("loginStatus")
        val USER_NAME = stringPreferencesKey("user_name")
    }



    //get saved access token

    val getAccessToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN] ?: ""
        }

    //save access token
    suspend fun saveAccessToken(token : String){
        context.dataStore.edit {preferences ->
            preferences[ACCESS_TOKEN] = token

        }
    }



    //user information from UserDetails
    val getUserName : Flow<String?> = context.dataStore.data
        .map { pref ->
            pref[USER_NAME]?: ""


        }
    suspend fun SaveUserName(name : String){
        context.dataStore.edit { pref ->
            pref[USER_NAME] = name


        }
    }


    val getUserEmail : Flow<String?> = context.dataStore.data
        .map { pref ->
            pref[USER_NAME]?: ""


        }
    suspend fun SaveUserEmail(email : String){
        context.dataStore.edit { pref ->
            pref[USER_NAME] = email


        }
    }



    val getUserNumber : Flow<String?> = context.dataStore.data
        .map { pref ->
            pref[USER_NAME]?: ""


        }
    suspend fun SaveUserNumber(number : String){
        context.dataStore.edit { pref ->
            pref[USER_NAME] = number


        }
    }



}
