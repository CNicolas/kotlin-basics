package bankaccount

interface BankEvent {
    val accountId: Int
}

class BankAccountCreated(override val accountId: Int, val owner: String) : BankEvent

class OwnerChanged(override val accountId: Int, val owner: String) : BankEvent

class DepositPerformed(override val accountId: Int, val amount: Int) : BankEvent

class WithdrawalPerformed(override val accountId: Int, val amount: Int) : BankEvent