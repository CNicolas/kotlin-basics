package football.player.strategy.combinated.attack

import football.player.Player
import football.player.SideInTeam
import football.player.strategy.AttackStrategy
import football.player.strategy.simple.attack.runAndShoot.cross.CrossShot
import football.player.strategy.simple.attack.runAndShoot.cross.RunZigZag
import helpers.Coordinates

class ZigZagAndCrossShot(override val side: SideInTeam) : AttackStrategy() {
    private val runningStrategy = RunZigZag(side)
    private val shootingStrategy = CrossShot(side)

    override fun move(player: Player): Coordinates {
        return runningStrategy.move(player)
    }

    override fun shoot(player: Player): Coordinates {
        return when {
            isAtShootingDistanceOfOpponentGoalCenter(player) -> this.shootingStrategy.shoot(player)
            else -> this.runningStrategy.shoot(player)
        }
    }
}