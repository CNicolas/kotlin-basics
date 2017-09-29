package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class BankAccountWithdrawalTest {
    @Test
    fun should_withdraw_20_on_created_account() {
        val eventHandler = BankEventHandler(BankEventStore())

        eventHandler.apply(BankAccountCreated(1, "John Doe"))
        eventHandler.apply(BankAccountWithdrawalPerformed(1, 20))

        val bankAccount = eventHandler.getAccountById(1)

        assertThat(bankAccount).isNotNull()
        assertThat(bankAccount?.id).isEqualTo(1)
        assertThat(bankAccount?.owner).isEqualTo("John Doe")
        assertThat(bankAccount?.balance).isEqualTo(-20)
    }
}