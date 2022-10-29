package asiankoala.ftc2022.opmodes

import com.asiankoala.koawalib.util.Alliance
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "\uD83E\uDD76")
class MiyukiBlueTeleOp : MiyukiTeleOp(Alliance.BLUE)

@TeleOp(name = "\uD83E\uDD75")
class MiyukiRedTeleOp : MiyukiTeleOp(Alliance.RED)

@Autonomous(name = "\uD83E\uDD76 CLOSE")
class MiyukiBlueCloseAuto : MiyukiAuto(TODO())

@Autonomous(name = "\uD83E\uDD76 FAR")
class MiyukiBlueFarAuto : MiyukiAuto(TODO())

@Autonomous(name = "\uD83E\uDD75 CLOSE")
class MiyukiRedCloseAuto : MiyukiAuto(TODO())

@Autonomous(name = "\uD83E\uDD75 FAR")
class MiyukiRedFarAuto : MiyukiAuto(TODO())
