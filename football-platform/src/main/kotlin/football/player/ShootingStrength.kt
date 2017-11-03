package football.player

enum class ShootingStrength(val strengthPercentage: Double) {
    RUN(0.5), NORMAL(1.0), SHOOT(2.0), CLEARANCE(5.0)
}