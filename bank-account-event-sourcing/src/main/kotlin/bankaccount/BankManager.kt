package bankaccount

import java.security.InvalidParameterException

class BankManager(val bank: Bank) {
    fun applyTo(event: BankEvent): BankAccount {
        val targetedBankAccount = bank.getBankAccountById(event.accountId)

        return when {
            targetedBankAccount !== null -> applyEventExceptCreation(event, targetedBankAccount)
            event is BankAccountCreated -> BankAccount(event.accountId, event.owner, 0)
            else -> throw InvalidParameterException("The bank account has not been found")
        }
    }

    private fun applyEventExceptCreation(event: BankEvent, targetedBankAccount: BankAccount): BankAccount {
        return when (event) {
            is DepositPerformed -> BankAccount(targetedBankAccount.id, targetedBankAccount.owner, targetedBankAccount.balance + event.amount)
            is WithdrawalPerformed -> BankAccount(targetedBankAccount.id, targetedBankAccount.owner, targetedBankAccount.balance - event.amount)
            is OwnerChanged -> BankAccount(targetedBankAccount.id, event.owner, targetedBankAccount.balance)
            else -> throw InvalidParameterException("The bank account already exists")
        }
    }
}