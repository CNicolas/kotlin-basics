package bankaccount

class Bank {
    var accounts: List<BankAccount> = listOf()

    fun getBankAccountById(id: Int): BankAccount? {
        return accounts.find { it.id == id }
    }
}