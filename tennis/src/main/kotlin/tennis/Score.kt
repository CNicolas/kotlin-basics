package tennis

enum class Score(val printableScore: String) {
    LOVE("love"), FIFTEEN("fifteen"), THIRTY("thirty"), FORTY("forty"), ADVANTAGE("advantage");

    fun next(): Score {
        if (this === FORTY) return ADVANTAGE
        if (this === THIRTY) return FORTY
        if (this === FIFTEEN) return THIRTY
        if (this === LOVE) return FIFTEEN
        return LOVE
    }
}