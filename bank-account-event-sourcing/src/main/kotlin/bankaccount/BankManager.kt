package bankaccount

class BankManager {
    fun applyTo(event: BankEvent, account: BankAccount?): BankAccount? {
        if (event is BankAccountCreated) {
            return BankAccount(event.accountId, event.owner, 0)
        }

        return null
    }
}