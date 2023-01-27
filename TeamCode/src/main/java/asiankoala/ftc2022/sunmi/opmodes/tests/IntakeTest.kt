package asiankoala.ftc2022.sunmi.opmodes.tests

import asiankoala.ftc2022.sunmi.State
import asiankoala.ftc2022.sunmi.Sunmi
import asiankoala.ftc2022.sunmi.commands.sequence.tele.GIDLE
import asiankoala.ftc2022.sunmi.commands.sequence.tele.IdleSeq
import asiankoala.ftc2022.sunmi.commands.sequence.tele.SmartIntakeSeq
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class IntakeTest : KOpMode(true, 8) {
    private lateinit var sunmi: Sunmi

    override fun mInit() {
        sunmi = Sunmi(Pose())
        sunmi.vision.unregister()
        sunmi.odo.unregister()
        driver.rightTrigger.onPress(SmartIntakeSeq(sunmi.claw, sunmi.arm))
    }

    override fun mStart() {
        + IdleSeq(sunmi)
    }
}