package asiankoala.ftc2022

import asiankoala.ftc2022.commands.CmdChooser
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import asiankoala.ftc2022.subsystems.Arm
import asiankoala.ftc2022.subsystems.Claw
import asiankoala.ftc2022.subsystems.Lift
import asiankoala.ftc2022.subsystems.Pivot
import com.asiankoala.koawalib.math.Pose

class Miyuki(startPose: Pose) {
    private val hardware = Hardware(startPose)

    val drive = KMecanumOdoDrive(
        hardware.fl,
        hardware.bl,
        hardware.fr,
        hardware.br,
        hardware.odometry,
        true
    )

    val lift = Lift(hardware.liftLeadMotor, hardware.liftFollowTop, hardware.liftFollowBottom)
    val arm = Arm(hardware.armMotor)
    val pivot = Pivot(hardware.pivotServo)
    val claw = Claw(hardware.clawLeftServo, hardware.clawRightServo, hardware.distanceSensor)

    val cmdChooser = CmdChooser()
}
