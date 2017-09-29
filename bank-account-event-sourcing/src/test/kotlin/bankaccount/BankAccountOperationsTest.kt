package bankaccount

import org.assertj.core.api.Assertions.assertThat

import org.testng.annotations.Test

class BankAccountOperationsTest {
    @Test
    fun should_change_owner_and_balance_10() {
        val bank = Bank()
        val bankManager = BankManager(bank)

        bankManager.applyTo(BankAccountCreated(1, "John Doe"))
        bankManager.applyTo(BankAccountDepositPerformed(1, 50))
        bankManager.applyTo(BankAccountOwnerChanged(1, "Jane Doe"))
        bankManager.applyTo(BankAccountWithdrawalPerformed(1, 40))

        assertThat(bank.accounts[0].id).isEqualTo(1)
        assertThat(bank.accounts[0].owner).isEqualTo("Jane Doe")
        assertThat(bank.accounts[0].balance).isEqualTo(10)

    }
}