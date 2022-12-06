package asiankoala.ftc2022.opmodes

import asiankoala.ftc2022.Miyuki
import com.asiankoala.koawalib.command.KOpMode
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class MiyukiTestAuto : KOpMode(photonEnabled = true) {
    private lateinit var miyuki: Miyuki

    override fun mInit() {
    }
}