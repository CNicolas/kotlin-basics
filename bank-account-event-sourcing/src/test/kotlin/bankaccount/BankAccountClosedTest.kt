package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountClosedTest {
    @Test
    fun should_close_when_BankAccountClosedEvent_is_send() {
        val initialBankAccount = BankAccount(1, "John Doe")
        val bank = Bank()
        bank.accounts.add(initialBankAccount)
        val bankManager = BankManager(bank)

        val event = BankAccountClosed(1)
        val bankAccount = bankManager.applyTo(event)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount.id).isEqualTo(1)
        assertThat(bankAccount.owner).isEqualTo("John Doe")
        assertThat(bankAccount.balance).isEqualTo(0)

        assertThat(bank.accounts).isEmpty()
    }
}