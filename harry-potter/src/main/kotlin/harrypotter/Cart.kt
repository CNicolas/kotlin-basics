package harrypotter

typealias BookPack = List<Book>
typealias BookPackList = List<BookPack>

class Cart(private val books: BookPack) {

    fun priceCart(): Double? {
        val allPacks: MutableList<BookPackList> = ArrayList()

        (1..minOf(5, books.size)).mapTo(allPacks) { splitCartInBookPacksWithMaximumSize(books, it) }

        val packPrices: List<Double> = allPacks.map {
            it.map { priceOfBookPackAfterDiscount(it) }
                    .reduce { pack1price, pack2price -> pack1price + pack2price }
        }

        return packPrices.min()
    }

    private fun splitCartInBookPacksWithMaximumSize(books: BookPack, maxDistinct: Int): BookPackList {
        val distinctPacks: MutableList<BookPack> = ArrayList()

        val currentBooks: MutableList<Book> = books.toMutableList()

        while (currentBooks.size > 0) {
            val currentDistinctBooks = getLargestDistinctBookPack(currentBooks, maxDistinct)
            distinctPacks.add(currentDistinctBooks)
            currentDistinctBooks.forEach { currentBooks.remove(it) }
        }

        return distinctPacks.toList()
    }

    private fun getLargestDistinctBookPack(books: BookPack, maxDistinct: Int): BookPack {
        val distinctBooks: MutableList<Book> = ArrayList()

        books.forEach { addIfNotPresentAndUnderMaximumPackSize(distinctBooks, maxDistinct, it) }

        return distinctBooks
    }

    private fun addIfNotPresentAndUnderMaximumPackSize(distinctBooks: MutableList<Book>, maxDistinct: Int, it: Book) {
        if (distinctBooks.size < maxDistinct && !distinctBooks.contains(it))
            distinctBooks.add(it)
    }

    private fun priceOfBookPackAfterDiscount(books: BookPack): Double {
        val basicPriceOfBooks = priceOfBooks(books)

        return when (books.size) {
            5 -> basicPriceOfBooks * 0.75
            4 -> basicPriceOfBooks * 0.80
            3 -> basicPriceOfBooks * 0.90
            2 -> basicPriceOfBooks * 0.95
            else -> basicPriceOfBooks.toDouble()
        }
    }

    private fun priceOfBooks(books: BookPack): Int =
            books.map { it.price }
                    .reduce { price1, price2 -> price1 + price2 }
}
