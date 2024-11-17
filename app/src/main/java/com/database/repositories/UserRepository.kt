package com.database.repositories

import com.database.dao.UserDao
import com.database.entities.User

class UserRepository(private val userDao: UserDao) {

    // Register a new user and return the new user ID
    suspend fun registerUser(user: User): Long {
        return userDao.registerUser(user)
    }

    // Delete a user by ID
    suspend fun deleteUserById(userId: Int) {
        userDao.deleteUserById(userId)
    }

    // Get a user by email (case insensitive)
    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    // Get a user by ID
    suspend fun getUserById(userId: Int): User? {
        return userDao.getUserById(userId)
    }
}