package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class WiringDriveTest : FourMotorTest(
    MotorFactory("fl").build(),
    MotorFactory("bl").build(),
    MotorFactory("br").build(),
    MotorFactory("fr").build()
)