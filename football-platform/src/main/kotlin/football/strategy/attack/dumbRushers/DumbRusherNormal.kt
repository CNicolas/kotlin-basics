package football.strategy.attack.dumbRushers

import helpers.ShootingStrength
import helpers.SideInTeam

class DumbRusherNormal(override val side: SideInTeam) : DumbRusher(ShootingStrength.NORMAL)