package bankaccount

import org.assertj.core.api.Assertions.assertThat
import org.testng.annotations.Test
import java.time.LocalDate

class BankQueryServiceTest {
    @Test
    fun should_retrieve_two_events_between_dates() {
        val eventStore = BankEventStore()
        val eventHandler = BankEventHandler(eventStore)
        val bankQueryService = BankQueryService(eventStore)

        eventHandler.apply(BankAccountCreated(1, "John Doe", LocalDate.of(2012, 1, 1)))
        eventHandler.apply(BankAccountDepositPerformed(1, 20, LocalDate.of(2013, 1, 1)))
        eventHandler.apply(BankAccountWithdrawalPerformed(1, 20, LocalDate.of(2013, 2, 1)))
        eventHandler.apply(BankAccountClosed(1, LocalDate.of(2015, 1, 1)))

        val bankEvents: List<BankEvent> = bankQueryService.getAccountEventsByIdBetweenDates(1,
                LocalDate.of(2012, 2, 1),
                LocalDate.of(2013, 3, 1))

        assertThat(bankEvents).isNotNull
        assertThat(bankEvents).isNotEmpty
        assertThat(bankEvents).hasSize(2)
    }
}