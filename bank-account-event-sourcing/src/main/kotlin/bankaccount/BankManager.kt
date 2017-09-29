package bankaccount

import java.security.InvalidParameterException

class BankManager(val bank: Bank) {
    fun applyTo(event: BankEvent): BankAccount {
        val targetedBankAccount = bank.getBankAccountById(event.accountId)

        return when {
            targetedBankAccount !== null -> applyEventExceptCreation(event, targetedBankAccount)
            event is BankAccountCreated -> bank.addAccount(BankAccount(event.accountId, event.owner, 0))
            else -> throw InvalidParameterException("The bank account has not been found")
        }
    }

    fun getAccounts(): List<BankAccount> = bank.accounts.toList()

    private fun applyEventExceptCreation(event: BankEvent, targetedBankAccount: BankAccount): BankAccount {
        return when (event) {
            is BankAccountDepositPerformed -> bank.modifyById(targetedBankAccount.id, createBankAccountAfterModifyingBalance(targetedBankAccount, event))
            is BankAccountWithdrawalPerformed -> bank.modifyById(targetedBankAccount.id, createBankAccountAfterModifyingBalance(targetedBankAccount, event))
            is BankAccountOwnerChanged -> bank.modifyById(targetedBankAccount.id, createBankAccountAfterOwnerChanged(targetedBankAccount, event))
            is BankAccountClosed -> bank.removeAccountById(targetedBankAccount.id)
            else -> throw InvalidParameterException("The bank account already exists")
        }
    }

    private fun createBankAccountAfterOwnerChanged(targetedBankAccount: BankAccount, event: BankAccountOwnerChanged) =
            BankAccount(targetedBankAccount.id, event.owner, targetedBankAccount.balance)

    private fun createBankAccountAfterModifyingBalance(targetedBankAccount: BankAccount, event: BankEvent) =
            BankAccount(targetedBankAccount.id, targetedBankAccount.owner, modifyBalance(targetedBankAccount, event))

    private fun modifyBalance(targetedBankAccount: BankAccount, event: BankEvent): Int =
            when (event) {
                is BankAccountWithdrawalPerformed -> targetedBankAccount.balance - event.amount
                is BankAccountDepositPerformed -> targetedBankAccount.balance + event.amount
                else -> throw InvalidParameterException("The event doesn't modify balance")
            }
}