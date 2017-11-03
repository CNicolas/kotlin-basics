package football.strategy.attack.dumbRushers

import helpers.ShootingStrength
import helpers.SideInTeam

class DumbRusherShoot(override val side: SideInTeam) : DumbRusher(ShootingStrength.SHOOT)