package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountDepositTest {
    @Test
    fun should_deposit_20_on_created_account() {
        val initialBankAccount = BankAccount(1, "John Doe")
        val bank = Bank()
        bank.accounts.add(initialBankAccount)
        val bankManager = BankManager(bank)

        val event = BankAccountDepositPerformed(1, 20)
        val bankAccount = bankManager.applyTo(event)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount.id).isEqualTo(1)
        assertThat(bankAccount.owner).isEqualTo("John Doe")
        assertThat(bankAccount.balance).isEqualTo(20)
    }
}