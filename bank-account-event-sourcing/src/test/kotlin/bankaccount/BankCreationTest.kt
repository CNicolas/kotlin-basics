package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankCreationTest {
    @Test
    fun should_create_BankAccount_when_BankAccountCreatedEvent_is_send() {
        val bankManager = BankManager(Bank())
        val event = BankAccountCreated(1, "John Doe")

        val bankAccount = bankManager.applyTo(event)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount.id).isEqualTo(1)
        assertThat(bankAccount.owner).isEqualTo("John Doe")
        assertThat(bankAccount.balance).isEqualTo(0)
    }
}