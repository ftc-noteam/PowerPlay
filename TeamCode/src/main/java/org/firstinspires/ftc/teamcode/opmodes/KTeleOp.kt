package org.firstinspires.ftc.teamcode.opmodes

import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.MecanumCmd
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Robot

@TeleOp
class KTeleOp : KOpMode() {
    private val robot by lazy { Robot() }

    override fun mInit() {
        robot.drive.setDefaultCommand(
            MecanumCmd(
                robot.drive,
                driver.leftStick.xInverted.yInverted,
                driver.rightStick.xInverted
            )
        )
    }
}
