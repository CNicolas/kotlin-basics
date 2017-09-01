package tennis

enum class Score {
    LOVE, FIFTEEN, THIRTY, FORTY;

    fun next(): Score {
        if (this == FIFTEEN) return THIRTY
        if (this == THIRTY) return FORTY
        if (this == LOVE) return FIFTEEN
        return LOVE
    }
}