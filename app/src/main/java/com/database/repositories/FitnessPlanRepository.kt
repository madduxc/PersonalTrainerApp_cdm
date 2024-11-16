package com.database.repositories

import com.database.dao.FitnessPlanDao
import com.database.entities.FitnessPlan

class FitnessPlanRepository(private val fitnessPlanDao: FitnessPlanDao) {

    // Insert a new fitness plan
    suspend fun insertFitnessPlan(fitnessPlan: FitnessPlan): Long {
        return fitnessPlanDao.insertFitnessPlan(fitnessPlan)
    }

    // Update an existing fitness plan
    suspend fun updateFitnessPlan(fitnessPlan: FitnessPlan) {
        fitnessPlanDao.updateFitnessPlan(fitnessPlan)
    }

    // Delete a fitness plan
    suspend fun deleteFitnessPlan(fitnessPlan: FitnessPlan) {
        fitnessPlanDao.deleteFitnessPlan(fitnessPlan)
    }

    // Get all fitness plans for a user
    suspend fun getFitnessPlansByUserId(userId: Int): List<FitnessPlan> {
        return fitnessPlanDao.getFitnessPlansByUserId(userId)
    }

    // Get a fitness plan by ID
    suspend fun getFitnessPlanById(fitnessPlanId: Int): FitnessPlan? {
        return fitnessPlanDao.getFitnessPlanById(fitnessPlanId)
    }
}
