package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountWithdrawalTest {
    @Test
    fun should_withdraw_20_on_created_account() {
        val initialBankAccount = BankAccount(1, "John Doe", 50)
        val bank = Bank()
        bank.accounts.add(initialBankAccount)
        val bankManager = BankManager(bank)

        val event = BankAccountWithdrawalPerformed(1, 20)
        val bankAccount = bankManager.applyTo(event)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount.id).isEqualTo(1)
        assertThat(bankAccount.owner).isEqualTo("John Doe")
        assertThat(bankAccount.balance).isEqualTo(30)
    }
}