package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountDepositTest {
    @Test
    fun should_deposit_20_on_created_account() {
        val eventStore = BankEventStore()
        val eventHandler = BankEventHandler(eventStore)
        val bankQueryService = BankQueryService(eventStore)

        eventHandler.apply(BankAccountCreated(1, "John Doe"))
        eventHandler.apply(BankAccountDepositPerformed(1, 20))

        val bankAccount = bankQueryService.getAccountById(1)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount?.id).isEqualTo(1)
        assertThat(bankAccount?.owner).isEqualTo("John Doe")
        assertThat(bankAccount?.balance).isEqualTo(20)
    }
}