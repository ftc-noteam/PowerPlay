package asiankoala.ftc2022.sunmi

import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.*
import asiankoala.ftc2022.sunmi.subsystems.constants.ArmConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.DriveConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.LiftConstants
import asiankoala.ftc2022.sunmi.subsystems.constants.PivotConstants
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive

class Sunmi(pose: Pose) {
//    val odo = KThreeWheelOdometry(
//        hw.leftEncoder,
//        hw.rightEncoder,
//        hw.auxEncoder,
//        OdoConstants.TRACK_WIDTH,
//        OdoConstants.PERP_TRACKER,
//        pose
//    )
//    val vision = Vision()
    val bl = MotorFactory("bl")
        .brake
        .reverse
        .build()
    val drive = KMecanumDrive(
        MotorFactory("fl")
            .brake
            .reverse
            .build(),
        bl,
        MotorFactory("br")
            .brake
            .forward
            .withStaticFeedforward(DriveConstants.brKStatic)
            .build(),

        MotorFactory("fr")
            .brake
            .forward
            .build()
    )
    val lift = Lift(bl)
    val claw = Claw()
    val pivot = Pivot()
    val arm = Arm()
    var strat = Strategy.LOW
    var state = State.IDLE
    var isStacking = false
    var stack = 5
    val liftCmd
        get() = LiftCmd(
            lift,
            when(strat) {
                Strategy.GROUND -> LiftConstants.ground
                Strategy.LOW -> LiftConstants.low
                Strategy.MED-> LiftConstants.med
                Strategy.HIGH -> LiftConstants.high
            }
        )

    val armCmd
        get() = ArmCmd(
            arm,
            when(strat) {
                Strategy.GROUND -> ArmConstants.gidle
                else -> ArmConstants.deposit
            }
        )

    val pivotCmd
        get() = PivotCmd(
            pivot,
            when(strat) {
                Strategy.GROUND -> PivotConstants.home
                else -> PivotConstants.deposit
            }
        )
}