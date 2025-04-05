package com.bcit.myminiapp

import androidx.compose.runtime.toMutableStateList
import com.bcit.myminiapp.data.ExcretionRepository
import com.bcit.myminiapp.data.LocalExcretion

class ExcretionState(private  val repository: ExcretionRepository) {

    var excretions = repository.getAll().toMutableStateList()

    fun add(localExcretion: LocalExcretion) {
        repository.insertEntity(localExcretion)
    }

    fun refresh() {
        excretions.apply {
            clear()
            addAll(repository.getAll())
        }
    }
}