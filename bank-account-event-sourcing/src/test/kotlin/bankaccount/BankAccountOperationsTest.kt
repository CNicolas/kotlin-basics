package bankaccount

import org.assertj.core.api.Assertions.assertThat

import org.testng.annotations.Test

class BankAccountOperationsTest {
    @Test
    fun should_change_owner_and_balance_20() {
        val eventHandler = BankEventHandler(BankEventStore())

        eventHandler.apply(BankAccountCreated(1, "John Doe"))
        eventHandler.apply(BankAccountDepositPerformed(1, 60))
        eventHandler.apply(BankAccountOwnerChanged(1, "Jane Doe"))
        eventHandler.apply(BankAccountWithdrawalPerformed(1, 40))

        val bankAccount = eventHandler.getAccountById(1)

        assertThat(bankAccount?.id).isEqualTo(1)
        assertThat(bankAccount?.owner).isEqualTo("Jane Doe")
        assertThat(bankAccount?.balance).isEqualTo(20)
    }
}