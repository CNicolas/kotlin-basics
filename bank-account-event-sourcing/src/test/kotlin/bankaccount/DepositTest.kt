package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class DepositTest {
    @Test
    fun should_deposit_20_on_created_account() {
        val bankManager = BankManager()
        val initialBankAccount = BankAccount(1, "John Doe")
        val event = DepositPerformed(1, 20)

        val bankAccount = bankManager.applyTo(event, initialBankAccount)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount?.id).isEqualTo(1)
        assertThat(bankAccount?.owner).isEqualTo("John Doe")
        assertThat(bankAccount?.balance).isEqualTo(20)
    }
}