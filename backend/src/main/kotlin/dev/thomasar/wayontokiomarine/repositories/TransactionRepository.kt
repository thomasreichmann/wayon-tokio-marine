package dev.thomasar.wayontokiomarine.repositories

import dev.thomasar.wayontokiomarine.models.Transaction
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface TransactionRepository : PagingAndSortingRepository<Transaction, Long>, CrudRepository<Transaction, Long> {
}