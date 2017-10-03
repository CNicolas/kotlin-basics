package bankaccount

import java.time.LocalDate

interface BankEvent {
    val accountId: Int
    val date: LocalDate

}

class BankAccountCreated(override val accountId: Int,
                         val owner: String,
                         override val date: LocalDate = LocalDate.now()) : BankEvent {
    companion object
}

class BankAccountClosed(override val accountId: Int,
                        override val date: LocalDate = LocalDate.now()) : BankEvent {
    companion object
}

class BankAccountOwnerChanged(override val accountId: Int,
                              val owner: String,
                              override val date: LocalDate = LocalDate.now()) : BankEvent {
    companion object
}

class BankAccountDepositPerformed(override val accountId: Int,
                                  val amount: Int,
                                  override val date: LocalDate = LocalDate.now()) : BankEvent {
    companion object
}

class BankAccountWithdrawalPerformed(override val accountId: Int,
                                     val amount: Int,
                                     override val date: LocalDate = LocalDate.now()) : BankEvent {
    companion object
}