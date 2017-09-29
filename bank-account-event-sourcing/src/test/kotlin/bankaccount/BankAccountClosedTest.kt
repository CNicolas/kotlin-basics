package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountClosedTest {
    @Test
    fun should_close_when_BankAccountClosedEvent_is_send() {
        val eventHandler = BankEventHandler(BankEventStore())

        eventHandler.apply(BankAccountCreated(1, "John Doe"))
        eventHandler.apply(BankAccountDepositPerformed(1, 20))
        eventHandler.apply(BankAccountClosed(1))

        val bankAccount = eventHandler.getAccountById(1)

        assertThat(bankAccount).isNull()
    }
}