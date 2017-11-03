package football.player.strategy.attack.dumbRushers

import football.player.ShootingStrength
import football.player.SideInTeam

class DumbRusherRun(override val side: SideInTeam) : DumbRusher(ShootingStrength.RUN)