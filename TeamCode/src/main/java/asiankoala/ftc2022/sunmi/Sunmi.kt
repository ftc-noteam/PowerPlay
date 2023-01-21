package asiankoala.ftc2022.sunmi

import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.*
import asiankoala.ftc2022.sunmi.subsystems.constants.*
import asiankoala.ftc2022.sunmi.subsystems.vision.Vision
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.KThreeWheelOdometry

class Sunmi(pose: Pose) {
    val vision = Vision()
    val fl = MotorFactory("fl")
        .brake
        .reverse
        .build()
    val bl = MotorFactory("bl")
        .brake
        .reverse
        .build()
    val br = MotorFactory("br")
            .brake
            .forward
            .withStaticFeedforward(DriveConstants.brKStatic)
            .build()

    val fr = MotorFactory("fr")
            .brake
            .forward
            .build()

    val odo = KThreeWheelOdometry(
        EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fl),
        EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(br),
        EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fr),
        OdoConstants.TRACK_WIDTH,
        OdoConstants.PERP_TRACKER,
        pose
    )
    val drive = KMecanumOdoDrive(fl, bl, br, fr, odo, true)
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