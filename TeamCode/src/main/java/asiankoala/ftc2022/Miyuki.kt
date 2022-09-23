package asiankoala.ftc2022

import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot

class Miyuki {
    private val hardware = Hardware()

    val odometry = KNewOdometry(
        hardware.leftEncoder,
        hardware.rightEncoder,
        hardware.auxEncoder,
        TODO(),
        TODO(),
        TODO()
    )

    val drive = KMecanumOdoDrive(
        hardware.flMotor,
        hardware.blMotor,
        hardware.frMotor,
        hardware.brMotor,
        odometry,
        false
    )

    val lift = Lift(hardware.liftLeadMotor, hardware.liftSecondMotor, hardware.liftThirdMotor)
    val arm = Arm(hardware.armMotor)
    val pivot = Pivot(hardware.pivotServo)
    val claw = Claw(hardware.clawLeftServo, hardware.clawRightServo, hardware.distanceSensor)
}