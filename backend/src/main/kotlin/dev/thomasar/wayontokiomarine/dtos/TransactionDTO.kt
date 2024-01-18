package dev.thomasar.wayontokiomarine.dtos

import java.math.BigDecimal
import java.time.LocalDate

data class TransactionDto(
    val originAccount: String,
    val destinationAccount: String,
    val amount: BigDecimal,
    val transferDate: LocalDate
)