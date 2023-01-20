package asiankoala.ftc2022.testing

import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class LiftTest : FourMotorTest(
    MotorFactory("lt").float.build(),
    MotorFactory("lb").float.reverse.build(),
    MotorFactory("rt").float.reverse.build(),
    MotorFactory("rb").float.build()
)