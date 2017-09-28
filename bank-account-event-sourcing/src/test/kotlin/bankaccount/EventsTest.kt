package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test

class EventsTest {
    @Test
    fun should_create_BankAccountCreated_event() {
        val event = BankAccountCreated(123, "John Doe")

        assertThat(event.accountId).isEqualTo(123)
        assertThat(event.owner).isEqualTo("John Doe")
    }

    @Test
    fun should_create_DepositPerformed_event() {
        val event = DepositPerformed(123, 20)

        assertThat(event.accountId).isEqualTo(123)
        assertThat(event.amount).isEqualTo(20)
    }

    @Test
    fun should_create_OwnerChanged_event() {
        val event = OwnerChanged(123, "John Doe")

        assertThat(event.accountId).isEqualTo(123)
        assertThat(event.owner).isEqualTo("John Doe")
    }

    @Test
    fun should_create_WithdrawalPerformed_event() {
        val event = WithdrawalPerformed(123, 20)

        assertThat(event.accountId).isEqualTo(123)
        assertThat(event.amount).isEqualTo(20)
    }
}