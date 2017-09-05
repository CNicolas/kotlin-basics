package harrypotter

class Cart(val books: List<Book>) {
    fun priceAfterDiscount(): Double =
            applyDiscount(books)

    private fun applyDiscount(books: List<Book>): Double {
        val remainingBooks: MutableList<Book> = books.toMutableList()
        var finalPrice = 0.0

        while (remainingBooks.size > 0) {
            val differentBooks: List<Book> = remainingBooks.distinctBy { book -> book.name }
            val priceOfDifferentBooks = priceOfBooks(differentBooks)

            differentBooks.forEach { remainingBooks.remove(it) }

            finalPrice += when (differentBooks.size) {
                5 -> priceOfDifferentBooks * 0.75
                4 -> priceOfDifferentBooks * 0.85
                3 -> priceOfDifferentBooks * 0.90
                2 -> priceOfDifferentBooks * 0.95
                else -> priceOfDifferentBooks.toDouble()
            }
        }

        return if (remainingBooks.size > 0)
            finalPrice + priceOfBooks(remainingBooks)
        else
            finalPrice
    }

    private fun priceOfBooks(books: List<Book>): Int =
            books.map { it.price }
                    .reduce { price1, price2 -> price1 + price2 }
}