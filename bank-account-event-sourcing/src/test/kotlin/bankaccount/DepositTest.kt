package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class DepositTest {
    @Test
    fun should_deposit_20_on_created_account() {
        val initialBankAccount = BankAccount(1, "John Doe")
        val bank = Bank()
        bank.accounts = listOf(initialBankAccount)
        val bankManager = BankManager(bank)

        val event = DepositPerformed(1, 20)
        val bankAccount = bankManager.applyTo(event)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount.id).isEqualTo(1)
        assertThat(bankAccount.owner).isEqualTo("John Doe")
        assertThat(bankAccount.balance).isEqualTo(20)
    }
}