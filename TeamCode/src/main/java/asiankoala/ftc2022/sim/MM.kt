package asiankoala.ftc2022.sim

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
        val intake = Pose2d(-12.0, -60.0, 270.0.radians)
        val otherDeposit = deposit.copy(y = 30.0, heading = (40.0 + 270.0).radians)
        val otherIntake = intake.copy(y = 60.0, heading = 90.0.radians)
        val conePickupTime = 0.5


        val bot = DefaultBotBuilder(mm)
            .setConstraints(130.0, 130.0, 180.0.radians, 180.0.radians, 13.3)
            .setDimensions(13.3, 13.3)
            .followTrajectorySequence {
                it.trajectorySequenceBuilder(Pose2d(-63.0, -36.0, 0.0))
                    .splineTo(Vector2d(-24.0, -36.0), 0.0)
                    .splineTo(deposit.vec(), deposit.heading)
                    .setReversed(true)
                    .splineTo(intake.vec(), intake.heading)
                    .waitSeconds(conePickupTime)
                    .setReversed(false)

                    .splineTo(deposit.vec(), deposit.heading)
                    .setReversed(true)
                    .splineTo(intake.vec(), intake.heading)
                    .waitSeconds(conePickupTime)
                    .setReversed(false)

                    .splineTo(deposit.vec(), deposit.heading)
                    .setReversed(true)
                    .splineTo(intake.vec(), intake.heading)
                    .waitSeconds(conePickupTime)
                    .setReversed(false)

                    .splineTo(deposit.vec(), deposit.heading)
                    .setReversed(true)
                    .splineTo(intake.vec(), intake.heading)
                    .waitSeconds(conePickupTime)
                    .setReversed(false)

                    .splineTo(deposit.vec(), deposit.heading)
                    .setReversed(true)
                    .splineTo(intake.vec(), intake.heading)
                    .waitSeconds(conePickupTime)
                    .setReversed(false)

                    .splineTo(Vector2d(-12.0, 0.0), 90.0.radians)
                    .splineTo(deposit.vec().copy(y = 18.0), 40.0.radians)
                    .splineTo(Vector2d(deposit.x - 2.0, 24.0), 160.0.radians)

                    .splineToSplineHeading(otherIntake.copy(heading = 270.0.radians), otherIntake.heading)
                    .waitSeconds(conePickupTime)

                    .setReversed(false)
                    .splineTo(otherDeposit.vec(), otherDeposit.heading)
                    .setReversed(true)
                    .splineTo(otherIntake.vec(), otherIntake.heading)
                    .waitSeconds(conePickupTime)

                    .setReversed(false)
                    .splineTo(otherDeposit.vec(), otherDeposit.heading)
                    .setReversed(true)
                    .splineTo(otherIntake.vec(), otherIntake.heading)
                    .waitSeconds(conePickupTime)

                    .setReversed(false)
                    .splineTo(otherDeposit.vec(), otherDeposit.heading)
                    .setReversed(true)
                    .splineTo(otherIntake.vec(), otherIntake.heading)
                    .waitSeconds(conePickupTime)

                    .setReversed(false)
                    .splineTo(otherDeposit.vec(), otherDeposit.heading)
                    .setReversed(true)
                    .splineTo(otherIntake.vec(), otherIntake.heading)
                    .waitSeconds(conePickupTime)

                    .setReversed(false)
                    .splineTo(otherDeposit.vec(), otherDeposit.heading)
                    .setReversed(true)
                    .splineTo(otherIntake.vec(), otherIntake.heading)
                    .waitSeconds(conePickupTime)

                    .build()
            }

        mm.setBackground(MeepMeep.Background.FIELD_POWERPLAY_KAI_DARK)
            .setDarkMode(true)
            .addEntity(bot)
            .start()
    }
}