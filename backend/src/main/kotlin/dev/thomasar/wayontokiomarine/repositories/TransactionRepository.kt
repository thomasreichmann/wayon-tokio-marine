package dev.thomasar.wayontokiomarine.repositories

import dev.thomasar.wayontokiomarine.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface TransactionRepository : JpaRepository<Transaction, Long> {
}