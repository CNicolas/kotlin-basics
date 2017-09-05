package harrypotter

class Cart(val books: List<Book>) {
    fun priceCart(): Double? {
        val allPacks: MutableList<List<List<Book>>> = ArrayList()
        (1..minOf(5, books.size)).mapTo(allPacks) { splitCartInPacksOfBooksWithMaximumSize(books, it) }

        val packPrices: List<Double> = allPacks.map {
            it.map { applyDiscountOnPackOfBooks(it) }
                    .reduce { pack1price, pack2price -> pack1price + pack2price }
        }

        return packPrices.min()
    }

    private fun splitCartInPacksOfBooksWithMaximumSize(books: List<Book>, maxDistinct: Int): List<List<Book>> {
        val distinctPacks: MutableList<List<Book>> = ArrayList()

        val currentBooks: MutableList<Book> = books.toMutableList()

        while (currentBooks.size > 0) {
            val currentDistinctBooks = getLargestDistinctPackOfBooks(currentBooks, maxDistinct)
            distinctPacks.add(currentDistinctBooks)
            currentDistinctBooks.forEach { currentBooks.remove(it) }
        }

        return distinctPacks.toList()
    }

    private fun getLargestDistinctPackOfBooks(books: List<Book>, maxDistinct: Int): List<Book> {
        val distinctBooks: MutableList<Book> = ArrayList()
        books.forEach {
            if (distinctBooks.size < maxDistinct && !distinctBooks.contains(it))
                distinctBooks.add(it)
        }

        return distinctBooks
    }

    private fun applyDiscountOnPackOfBooks(books: List<Book>): Double {
        val basicPriceOfBooks = priceOfBooks(books)
        return when (books.size) {
            5 -> basicPriceOfBooks * 0.75
            4 -> basicPriceOfBooks * 0.80
            3 -> basicPriceOfBooks * 0.90
            2 -> basicPriceOfBooks * 0.95
            else -> basicPriceOfBooks.toDouble()
        }
    }

    private fun priceOfBooks(books: List<Book>): Int =
            books.map { it.price }
                    .reduce { price1, price2 -> price1 + price2 }
}