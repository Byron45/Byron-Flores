package com.example.app_vehiculos.controller

import com.example.app_vehiculos.model.User
import com.example.app_vehiculos.model.UserDao

class AuthController(private val userDao: UserDao) {

    suspend fun login(name: String, lastName: String): Boolean{
        val username = "$name$lastName".lowercase()
        return userDao.getUserByUsername(username) != null
    }

    suspend fun register(name: String, lastName: String): Boolean{
        val username = "$name$lastName".lowercase()
        val userExists = userDao.getUserByUsername(username) != null
        if(userExists) return false

        val newUSer = User(username = username)
        userDao.insertUser(newUSer)
        return true
    }

}