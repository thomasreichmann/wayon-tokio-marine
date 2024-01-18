package dev.thomasar.wayontokiomarine.controllers

import dev.thomasar.wayontokiomarine.dtos.TransactionDto
import dev.thomasar.wayontokiomarine.models.Transaction
import dev.thomasar.wayontokiomarine.services.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/transactions")
class TransactionController @Autowired constructor(
    private val transactionService: TransactionService
) {

    @GetMapping
    fun getTransactions(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<Page<Transaction>> {
        val transactionsPage = transactionService.getTransactions(page, size)
        return ResponseEntity.ok(transactionsPage)
    }

    @PostMapping
    fun createTransaction(
        @RequestBody transactionDto: TransactionDto
    ): ResponseEntity<Transaction> {
        val transaction = transactionService.createTransaction(
            transactionDto.originAccount,
            transactionDto.destinationAccount,
            transactionDto.amount,
            transactionDto.transferDate
        )
        return ResponseEntity(transaction, HttpStatus.CREATED)
    }
}
