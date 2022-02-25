package com.ods.myunittests.data

class SpendTrackerDataSource(private val dao: SpendDao) {
    suspend fun addSpend(spend: Spend) = dao.addSpend(spend)

    suspend fun getLast20Spends() = dao.getLast20Spends()
}