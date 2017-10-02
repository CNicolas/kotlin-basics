package bankaccount

import java.time.LocalDate

class BankQueryService(private val eventStore: BankEventStore) {
    fun getAccountById(accountId: Int): BankAccount? {
        val accountEvents = eventStore.events.filter { it.accountId == accountId }

        return calculateAccountStatus(accountEvents)
    }

    fun getAccountEventsByIdBetweenDates(accountId: Int, startDate: LocalDate, endDate: LocalDate = LocalDate.now()): List<BankEvent> {
        return eventStore.events
                .filter { it.accountId == accountId && it.date >= startDate && it.date <= endDate }
    }

    private fun calculateAccountStatus(events: List<BankEvent>): BankAccount? {
        val manager = BankCommands(Bank())

        events.forEach { manager.applyTo(it) }

        return if (manager.getAccounts().isNotEmpty()) {
            manager.getAccounts().last()
        } else {
            null
        }
    }
}