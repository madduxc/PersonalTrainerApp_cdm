package com.database.repositories

import com.database.dao.UserDao
import com.database.entities.User

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(user: User): Long {
        return userDao.registerUser(user)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }
}