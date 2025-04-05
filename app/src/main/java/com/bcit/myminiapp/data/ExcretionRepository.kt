package com.bcit.myminiapp.data

class ExcretionRepository(private val excretionDao: ExcretionDao) {

    // data access logic
    // layer of abstract between data sources and business logic

    fun insertEntity(user: LocalExcretion) {
        excretionDao.add(user)
    }

    fun getAll(): List<LocalExcretion> {
        return excretionDao.getAll()
    }

}