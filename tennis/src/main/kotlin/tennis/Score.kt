package tennis

enum class Score(val printableScore: String) {
    LOVE("love"), FIFTEEN("fifteen"), THIRTY("thirty"), FORTY("forty"), ADVANTAGE("advantage"), WON("won");

    fun next(): Score {
        if (this === ADVANTAGE) return WON
        if (this === FORTY) return ADVANTAGE
        if (this === THIRTY) return FORTY
        if (this === FIFTEEN) return THIRTY
        if (this === LOVE) return FIFTEEN
        return LOVE
    }
}