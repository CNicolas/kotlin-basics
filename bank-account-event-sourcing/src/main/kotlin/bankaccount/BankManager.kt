package bankaccount

class BankManager {
    fun applyTo(event: BankEvent, account: BankAccount?): BankAccount? {
        if (event is DepositPerformed && account !== null) {
            return BankAccount(account.id, account.owner, account.balance + event.amount)
        } else if (event is BankAccountCreated) {
            return BankAccount(event.accountId, event.owner, 0)
        }

        return null
    }
}