package dev.thomasar.wayontokiomarine.services

import dev.thomasar.wayontokiomarine.model.Transaction
import dev.thomasar.wayontokiomarine.repositories.TransactionRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal
import java.time.LocalDate

class TransactionServiceTest {
    @Mock
    private lateinit var transactionRepository: TransactionRepository

    private lateinit var transactionService: TransactionService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        transactionService = TransactionService(transactionRepository)
    }

    @Test
    fun `calculateFee - applies correct fee logic based on days until transfer`() {
        val daysUntilTransfer = listOf(0L, 5L, 15L, 25L, 35L, 45L)
        val expectedFees = listOf("5.50", "12.00", "8.20", "6.90", "4.70", "1.70")

        daysUntilTransfer.zip(expectedFees).forEach { (days, expectedFee) ->
            val transaction = createTestTransaction(days)
            val fee = transactionService.calculateFee(transaction).setScale(2)
            assertEquals(BigDecimal(expectedFee), fee)
        }
    }

    @Test
    fun `createTransaction - creates and saves transaction`() {
        val mockTransaction = Transaction(5L)
        Mockito.`when`(transactionRepository.save(any())).thenReturn(mockTransaction)

        val createdTransaction = transactionService.createTransaction(mockTransaction.originAccount, mockTransaction.destinationAccount, mockTransaction.amount, mockTransaction.transferDate)

        assertEquals(mockTransaction, createdTransaction)
    }

    // Utility method for creating test transactions
    private fun createTestTransaction(daysUntilTransfer: Long): Transaction {
        val amount = BigDecimal("100.00")
        val schedulingDate = LocalDate.now()
        val transferDate = schedulingDate.plusDays(daysUntilTransfer)
        val transaction  =Transaction(null, "1234567890", "0987654321", amount, transferDate, schedulingDate)

        transaction.fee = transactionService.calculateFee(transaction)
        return transaction
    }
}