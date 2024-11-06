package com.database.repositories

import com.database.dao.ProfileDao
import com.database.entities.FitnessPlan
import com.database.entities.FitnessPlanExercise
import com.database.entities.Profile

class ProfileRepository(
    private val profileDao: ProfileDao,
) {

    suspend fun createProfile(profile: Profile): Long {
        return profileDao.createProfile(profile)
    }

    suspend fun createFitnessPlan(fitnessPlan: FitnessPlan): Long {
        return profileDao.createFitnessPlan(fitnessPlan)
    }

    suspend fun addExerciseToFitnessPlan(fitnessPlanId: Int, exerciseId: Int) {
        profileDao.addSingleExerciseToFitnessPlan(
            FitnessPlanExercise(fitnessPlanId = fitnessPlanId, exerciseId = exerciseId)
        )
    }

    suspend fun removeExerciseFromFitnessPlan(fitnessPlanId: Int, exerciseId: Int) {
        profileDao.deleteExerciseFromFitnessPlan(fitnessPlanId, exerciseId)
    }
}
