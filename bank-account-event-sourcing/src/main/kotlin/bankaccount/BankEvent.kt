package bankaccount

interface BankEvent {
    val accountId: Int
}

class BankAccountCreated(override val accountId: Int, val owner: String) : BankEvent

class BankAccountClosed(override val accountId: Int) : BankEvent

class BankAccountOwnerChanged(override val accountId: Int, val owner: String) : BankEvent

class BankAccountDepositPerformed(override val accountId: Int, val amount: Int) : BankEvent

class BankAccountWithdrawalPerformed(override val accountId: Int, val amount: Int) : BankEvent