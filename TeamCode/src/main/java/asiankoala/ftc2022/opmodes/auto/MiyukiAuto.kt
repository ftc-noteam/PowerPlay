package asiankoala.ftc2022.opmodes.auto

//open class MiyukiAuto(
//    alliance: Alliance,
//    close: Boolean,
//) : KOpMode() {
//    private val startPose = Pose(
//        Vector(-66.0, -36.0).choose(alliance, close),
//        close.choose(0.0, 180.0.radians)
//    )
//    private val miyuki by lazy { Miyuki(startPose) }
//    private val kN = 0.6
//    private val kOmega = 1.0 / 30.0.radians
//    private val kF = 4.0
//    private val kS = 1.0
//    private val epsilon = 1.0
//    private val thetaEpsilon = 5.0
//
//    private val initialPath = HermitePath(
//        FLIPPED_HEADING_CONTROLLER,
//        Pose(-66.0, -36.0, 0.0),
//        Pose(-3.0, -28.0, 30.0.radians)
//    ).choose(alliance, close)
//
//    private val intakePath = HermitePath(
//        DEFAULT_HEADING_CONTROLLER,
//        Pose(-3.0, -28.0, 210.0.radians),
//        Pose(-12.0, -66.0, 270.0.radians)
//    ).choose(alliance, close)
//
//    private val depositPath = HermitePath(
//        FLIPPED_HEADING_CONTROLLER,
//        Pose(-12.0, -66.0, 90.0.radians),
//        Pose(-3.0, -28.0, 30.0.radians)
//    ).choose(alliance, close)
//
//    private val initialReadyP = Pair(
//        ReadySequence(miyuki), Vector(-24.0, -36.0).choose(alliance, close)
//    )
//
//    private val intakeP = Pair(
//        IntakeSequence(miyuki.claw), Vector(-12.0, -43.0).choose(alliance, close)
//    )
//
//    private val readyP = Pair(
//        ReadySequence(miyuki), Vector(-12.0, -36.0).choose(alliance, close)
//    )
//
//    private val depositP = Pair(
//        DepositSequence(miyuki), Vector(-5.0, -30.0).choose(alliance, close)
//    )
//
//    private fun getGVFCmd(path: Path, vararg cmds: Pair<Cmd, Vector>) =
//        GVFCmd(miyuki.drive, SimpleGVFController(path, kN, kOmega, kF, kS, epsilon, thetaEpsilon), *cmds)
//
//    override fun mInit() {
//        +SequentialGroup(
//            ClawCmds.ClawGripCmd(miyuki.claw),
//            CmdChooser.homeCmd(miyuki),
//            WaitUntilCmd { opModeState == OpModeState.START },
//            getGVFCmd(initialPath, initialReadyP, depositP),
//            *List(5) {
//                listOf(
//                    getGVFCmd(intakePath, intakeP),
//                    getGVFCmd(depositPath, readyP, depositP)
//                )
//            }.flatten().toTypedArray()
//        )
//    }
//
//    companion object {
//        private fun <T> Boolean.choose(a: T, b: T) = if (this) a else b
//        private fun <T> T.cond(cond: Boolean, f: (T) -> T) = cond.choose(f.invoke(this), this)
//
//        private fun Vector.choose(alliance: Alliance, close: Boolean) =
//            this
//                .cond(alliance == Alliance.RED) { Vector(-x, y) }
//                .cond(close) { Vector(x, -y) }
//
//        private fun HermitePath.choose(alliance: Alliance, close: Boolean) =
//            this
//                .cond(alliance == Alliance.RED) {
//                    this.map(FLIPPED_HEADING_CONTROLLER) {
//                        Pose(
//                            -it.x,
//                            it.y,
//                            (180.0.radians - it.heading).angleWrap
//                        )
//                    }
//                }
//                .cond(close) {
//                    this.map(DEFAULT_HEADING_CONTROLLER) {
//                        Pose(
//                            it.x,
//                            -it.y,
//                            -it.heading
//                        )
//                    }
//                }
//    }
//}
