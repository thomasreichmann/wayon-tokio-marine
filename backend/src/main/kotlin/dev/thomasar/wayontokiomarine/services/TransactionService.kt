package dev.thomasar.wayontokiomarine.services

import dev.thomasar.wayontokiomarine.exceptions.NoApplicableFeeException
import dev.thomasar.wayontokiomarine.models.Transaction
import dev.thomasar.wayontokiomarine.repositories.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Service
class TransactionService @Autowired constructor(val transactionRepository: TransactionRepository) {
    fun getTransactions(page: Int = 0, size: Int = 10): Page<Transaction> {
        return transactionRepository.findAll(PageRequest.of(page, size))
    }

    fun createTransaction(originAccount: String, destinationAccount: String, amount: BigDecimal, transferDate: LocalDate): Transaction {
        val schedulingDate = LocalDate.now()
        val transaction = Transaction(null, originAccount, destinationAccount, amount, transferDate, schedulingDate)

        transaction.fee = calculateFee(transaction)

        return transactionRepository.save(transaction)
    }

    fun calculateFee(transaction: Transaction): BigDecimal {
        val daysBetween = ChronoUnit.DAYS.between(transaction.schedulingDate, transaction.transferDate)

        return when (daysBetween) {
            0L -> transaction.amount.multiply(BigDecimal("0.025")).add(BigDecimal("3.00")) // 2.5% + R$ 3.00
            in 1L..10L -> BigDecimal("12.00") // Flat fee of R$ 12.00
            in 11L..20L -> transaction.amount.multiply(BigDecimal("0.082")) // 8.2%
            in 21L..30L -> transaction.amount.multiply(BigDecimal("0.069")) // 6.9%
            in 31L..40L -> transaction.amount.multiply(BigDecimal("0.047")) // 4.7%
            in 41L..50L -> transaction.amount.multiply(BigDecimal("0.017")) // 1.7%
            else -> throw NoApplicableFeeException("No applicable fee for $daysBetween")
        }
    }
}