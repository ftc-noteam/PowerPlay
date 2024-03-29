package asiankoala.ftc2022.sunmi

import asiankoala.ftc2022.sunmi.commands.subsystem.ArmCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.LiftCmd
import asiankoala.ftc2022.sunmi.commands.subsystem.PivotCmd
import asiankoala.ftc2022.sunmi.subsystems.*
import asiankoala.ftc2022.sunmi.subsystems.constants.*
import asiankoala.ftc2022.sunmi.subsystems.vision.Vision
import com.asiankoala.koawalib.hardware.motor.EncoderFactory
import com.asiankoala.koawalib.hardware.motor.MotorFactory
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.subsystem.drive.KMecanumOdoDrive
import com.asiankoala.koawalib.subsystem.odometry.KOffsetOdometry

class Sunmi(pose: Pose) {
    private val fl = MotorFactory("fl")
        .brake
        .reverse
        .build()
    private val bl = MotorFactory("bl")
        .brake
        .reverse
        .withStaticFeedforward(DriveConstants.blKStatic)
        .build()
    private val br = MotorFactory("br")
        .brake
        .forward
        .withStaticFeedforward(DriveConstants.brKStatic)
        .build()
    private val fr = MotorFactory("fr")
        .brake
        .forward
        .build()

//    val odo = KThreeWheelOdometry(
//        leftEncoder = EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fr),
//        rightEncoder = EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fl),
//        auxEncoder = EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(bl),
//        OdoConstants.TRACK_WIDTH,
//        OdoConstants.PERP_TRACKER,
//        pose
//    )
    val odo = KOffsetOdometry(
        leftEncoder = EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fr),
        rightEncoder = EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(fl),
        auxEncoder = EncoderFactory(OdoConstants.ticksPerUnit).revEncoder.build(bl),
        OdoConstants.LEFT_OFFSET,
        OdoConstants.RIGHT_OFFSET,
        OdoConstants.PERP_TRACKER,
        pose
    )
    val drive = KMecanumOdoDrive(fl, bl, br, fr, odo, false)
    val vision = Vision()
    val lift = Lift(br)
    val claw = Claw()
    val pivot = Pivot()
    val arm = Arm()
    var strat = Strategy.LOW
    var state = State.IDLE
    var isStacking = false
    var stack = 5
    val stackHeight get() = (stack - 1) - 0.2
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
                Strategy.GROUND -> ArmConstants.ground
                else -> ArmConstants.deposit
            }
        )

    val pivotCmd
        get() = PivotCmd(
            pivot,
            when(strat) {
                Strategy.GROUND -> PivotConstants.ground
                else -> PivotConstants.deposit
            }
        )
}