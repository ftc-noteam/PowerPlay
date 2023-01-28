package asiankoala.ftc2022.sim

import asiankoala.ftc2022.sunmi.subsystems.constants.FieldConstants
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder
import com.asiankoala.koawalib.math.radians
import com.noahbres.meepmeep.MeepMeep
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder

object MM {
    @JvmStatic
    fun main(args: Array<String>) {
        val mm = MeepMeep(800, 144)
        val deposit = Pose2d(-6.0, -30.0, 40.0.radians)
        val intake = Pose2d(-12.0, -65.0, 270.0.radians)
        val conePickupTime = 0.5


        val bot = DefaultBotBuilder(mm)
            .setConstraints(60.0, 60.0, 180.0.radians, 180.0.radians, 13.3)
            .setDimensions(13.3, 13.3)
            .followTrajectorySequence {
                it.trajectorySequenceBuilder(Pose2d(FieldConstants.startX, FieldConstants.startY, 0.0))
                    .splineTo(Vector2d(-48.0, -34.0), (-10.0).radians)
                    .splineTo(Vector2d(-24.0, -38.0), 0.0)
                    .splineTo(Vector2d(FieldConstants.depositX, FieldConstants.depositY), 45.0.radians)
//                    .setReversed(true)
//                    .splineTo(Vector2d(-12.0, -48.0), intake.heading)
//                    .splineTo(intake.vec(), intake.heading)
//                    .waitSeconds(conePickupTime)
//                    .setReversed(false)

//                    .splineTo(deposit.vec(), deposit.heading)
//                    .setReversed(true)
//                    .splineTo(intake.vec(), intake.heading)
//                    .waitSeconds(conePickupTime)
//                    .setReversed(false)

                    .build()
            }

        mm.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
            .setDarkMode(true)
            .addEntity(bot)
            .start()
    }
}