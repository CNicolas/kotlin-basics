package bankaccount

class BankEventHandler(private val eventStore: BankEventStore) {
    fun apply(event: BankEvent) {
        eventStore.events.add(event)
    }
}