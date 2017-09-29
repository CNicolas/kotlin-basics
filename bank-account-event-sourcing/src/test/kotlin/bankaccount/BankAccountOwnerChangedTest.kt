package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountOwnerChangedTest {
    @Test
    fun should_deposit_20_on_created_account() {
        val initialBankAccount = BankAccount(1, "John Doe")
        val bank = Bank()
        bank.accounts.add(initialBankAccount)
        val bankManager = BankManager(bank)

        val event = BankAccountOwnerChanged(1, "Jane Doe")
        val bankAccount = bankManager.applyTo(event)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount.id).isEqualTo(1)
        assertThat(bankAccount.owner).isEqualTo("Jane Doe")
        assertThat(bankAccount.balance).isEqualTo(0)
    }
}