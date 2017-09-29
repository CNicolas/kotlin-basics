package bankaccount

class BankEventHandler(val eventStore: BankEventStore) {
    fun apply(event: BankEvent) {
        eventStore.events.add(event)
    }

    fun getAccountById(accountId: Int): BankAccount? {
        val accountEvents = eventStore.events.filter { it.accountId == accountId }

        return calculateAccountStatus(accountEvents)
    }

    private fun calculateAccountStatus(events: List<BankEvent>): BankAccount? {
        val manager = BankManager(Bank())

        events.forEach { manager.applyTo(it) }

        return if (manager.getAccounts().isNotEmpty()) {
            manager.getAccounts().last()
        } else {
            null
        }
    }
}