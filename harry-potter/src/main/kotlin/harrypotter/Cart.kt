package harrypotter

typealias BookPack = List<Book>
typealias BookPackList = List<BookPack>

class Cart(private val books: BookPack) {
    private val discountTable: Map<Int, Double> = hashMapOf(
            2 to 5.0,
            3 to 10.0,
            4 to 20.0,
            5 to 25.0)

    fun priceCart(): Double? {
        val allPacks: MutableList<BookPackList> = ArrayList()

        val maximumPackSize: Int = minOf(HarryPotterBook.values().size, books.size)
        (1..maximumPackSize)
                .mapTo(allPacks) { splitCartInBookPacksWithMaximumSize(books, it) }

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

        if (discountTable.containsKey(books.size))
            return basicPriceOfBooks * getDiscount(books)

        return basicPriceOfBooks.toDouble()
    }

    private fun getDiscount(books: BookPack): Double = ((100.0 - discountTable.getValue(books.size)) / 100.0)

    private fun priceOfBooks(books: BookPack): Int =
            books.map { it.price }
                    .reduce { price1, price2 -> price1 + price2 }
}
