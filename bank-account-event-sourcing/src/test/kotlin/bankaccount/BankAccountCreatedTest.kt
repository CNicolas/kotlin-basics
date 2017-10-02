package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountCreatedTest {
    @Test
    fun should_create_BankAccount_when_BankAccountCreatedEvent_is_send() {
        val eventStore = BankEventStore()
        val eventHandler = BankEventHandler(eventStore)
        val bankQueryService = BankQueryService(eventStore)

        eventHandler.apply(BankAccountCreated(1, "John Doe"))

        val bankAccount = bankQueryService.getAccountById(1)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount?.id).isEqualTo(1)
        assertThat(bankAccount?.owner).isEqualTo("John Doe")
        assertThat(bankAccount?.balance).isEqualTo(0)
    }
}