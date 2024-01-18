package dev.thomasar.wayontokiomarine.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.math.BigDecimal
import java.time.LocalDate

@Entity
class Transaction(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var originAccount: String = "",
    var destinationAccount: String = "",
    var amount: BigDecimal = BigDecimal.ZERO,
    var transferDate: LocalDate = LocalDate.now(),
    var schedulingDate: LocalDate = LocalDate.now(),
    var fee: BigDecimal = BigDecimal.ZERO
)
