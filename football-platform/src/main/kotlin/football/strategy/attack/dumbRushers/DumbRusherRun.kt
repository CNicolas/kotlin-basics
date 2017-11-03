package football.strategy.attack.dumbRushers

import helpers.ShootingStrength
import helpers.SideInTeam

class DumbRusherRun(override val side: SideInTeam) : DumbRusher(ShootingStrength.RUN)