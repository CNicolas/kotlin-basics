package bankaccount

class Bank {
    val accounts: MutableList<BankAccount> = mutableListOf()

    fun getBankAccountById(id: Int): BankAccount? {
        return accounts.find { it.id == id }
    }

    fun modifyById(id: Int, newBankAccount: BankAccount): BankAccount {
        val index = accounts.indexOf(getBankAccountById(id))
        accounts[index] = newBankAccount

        return accounts[index]
    }

    fun removeAccountById(id: Int): BankAccount {
        val accountToDelete = getBankAccountById(id)
        accounts.remove(accountToDelete)

        return accountToDelete!!
    }

    fun addAccount(account: BankAccount): BankAccount {
        accounts.add(account)
        return accounts.last()
    }
}