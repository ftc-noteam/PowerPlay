package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.subsystem.drive.KMecanumDrive

class Robot {
    private val hardware = Hardware()

    val drive = KMecanumDrive(
        hardware.fl,
        hardware.bl,
        hardware.br,
        hardware.fr,
    )
}