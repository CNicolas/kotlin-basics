package data.illness

class Diagnostic {
    var soreThroat: Boolean = false
    var cold: Boolean = false
    var lungs: Boolean = false
    var bronchi: Boolean = false

    var back: Boolean = false
    var waist: Boolean = false
    var neck: Boolean = false

    var eye: Boolean = false
    var headAche: Boolean = false

    fun toArray(): Array<Boolean> = arrayOf(soreThroat, cold, lungs, bronchi, back, waist, neck, eye, headAche)
}