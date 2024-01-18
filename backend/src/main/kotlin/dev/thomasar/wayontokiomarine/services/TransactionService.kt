package dev.thomasar.wayontokiomarine.services

import dev.thomasar.wayontokiomarine.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService @Autowired constructor(transactionRepository: TransactionRepository) {

}