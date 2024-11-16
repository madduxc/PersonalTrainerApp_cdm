package com.database.repositories

import com.database.dao.ProfileDao
import com.database.entities.Profile

class ProfileRepository(private val profileDao: ProfileDao) {

    // Create or update a profile
    suspend fun createProfile(profile: Profile) {
        profileDao.createProfile(profile)
    }

    // Get a user's profile by user ID
    suspend fun getUserProfile(userId: Int): Profile? {
        return profileDao.getUserProfile(userId)
    }

    // Update a profile
    suspend fun updateProfile(profile: Profile) {
        profileDao.updateProfile(profile)
    }

    // Delete a profile
    suspend fun deleteProfile(profile: Profile) {
        profileDao.deleteProfile(profile)
    }
}
