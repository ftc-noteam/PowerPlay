package asiankoala.ftc2022.sunmi.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.hardware.servo.KServo
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class ZeroTheServo : KOpMode() {
    lateinit var servo: KServo

    override fun mInit() {
        servo = KServo("claw").startAt(0.5)
    }
}
