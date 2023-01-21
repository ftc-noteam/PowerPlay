package asiankoala.ftc2022.sunmi.opmodes

import com.asiankoala.koawalib.command.KOpMode

open class SunmiRightAuto : KOpMode(true) {
//    private lateinit var sunmi: Sunmi
//    protected open val start = Pose(-63.0, -36.0, 0.0)
//    protected open val initReady = Pose(-24.0, -36.0, 0.0)
//    protected open val initDeposit = Pose(-5.0, -29.0, 40.0.radians)
//    protected open val deposit = Pose(-3.0, -30.0, 60.0.radians)
//    protected open val intake = Pose(-12.0, -60.0, 270.0.radians)
//
//    private fun getGvfCmd(
//        path: HermitePath,
//        vararg cmds: ProjQuery,
//        kN: Double = 0.6,
//        kOmega: Double = 30.0,
//        kF: Double = 4.0,
//        kS: Double = 0.8,
//        epsilon: Double = 2.0,
//        thetaEpsilon: Double = 2.0
//    ) = GVFCmd(
//        sunmi.drive,
//        SimpleGVFController(path, sunmi.drive, kN, kOmega, kF, kS, epsilon, thetaEpsilon),
//        *cmds
//    )
//
//    private val initDepositPath by lazy {
//        HermitePath(
//            DEFAULT_HEADING_CONTROLLER,
//            start,
//            initReady,
//            initDeposit
//        )
//    }
//    private val intakePath by lazy {
//        HermitePath(
//            DEFAULT_HEADING_CONTROLLER.flip(),
//            deposit.copy(heading = 60.0.radians),
//            intake
//        )
//    }
//    private val depositPath by lazy { intakePath.flip() }
//    private val initCmd = getGvfCmd(
//        initDepositPath,
//        ProjQuery(ParallelGroup(
//            LiftStateCmd(sunmi.lift, Strategy.HIGH),
//            PivotStateCmd(sunmi.pivot, Strategy.HIGH),
//            ArmStateCmd(sunmi.arm, Strategy.HIGH)
//        ), 0.5),
//        ProjQuery(AutoDepositSeq(sunmi), 0.99)
//    )
//    private val intakeCmd = getGvfCmd(intakePath, ProjQuery(AutoIntakeSeq(sunmi), 0.99))
//    private val depositCmd = getGvfCmd(
//        depositPath,
//        ProjQuery(LiftStateCmd(sunmi.lift, Strategy.HIGH), 0.5),
//        ProjQuery(AutoDepositSeq(sunmi), 0.99)
//    )
//    private val endCmd by lazy {
//        ChooseCmd(
//            getGvfCmd(
//                HermitePath(
//                    { 90.0 },
//                    deposit.copy(heading = 90.0.radians),
//                    deposit.copy(y = deposit.y + 18.0, heading = 90.0.radians)
//                ),
//                ProjQuery(LiftHomeCmd(sunmi.lift), 0.5)
//            ),
//            ChooseCmd(
//                LiftHomeCmd(sunmi.lift),
//                getGvfCmd(
//                    HermitePath(
//                        { 90.0 },
//                        deposit.copy(heading = 270.0.radians),
//                        deposit.copy(y = deposit.y - 36.0, heading = 270.0.radians)
//                    ),
//                    ProjQuery(LiftHomeCmd(sunmi.lift), 0.5)
//                )
//            ) { sunmi.vision.zone == Zones.MIDDLE }
//        ) { sunmi.vision.zone == Zones.LEFT }
//    }
//
//    override fun mInit() {
//        Logger.config = LoggerConfig.DASHBOARD_CONFIG
//        sunmi = Sunmi(start)
//
//        + SequentialGroup(
//            ClawOpenCmd(sunmi.claw),
//            ArmCmd(sunmi.arm, 90.0),
//            WaitUntilCmd(driver.rightTrigger::isJustPressed),
//            ClawGripCmd(sunmi.claw),
//            WaitUntilCmd { opModeState == OpModeState.START },
//            initCmd,
//            WaitCmd(3.0),
//            intakeCmd,
//            WaitCmd(3.0),
//            depositCmd,
//            WaitCmd(3.0),
//            endCmd
//        )
//    }
//
//    override fun mInitLoop() {
//        Logger.addTelemetryData("zone", sunmi.vision.zone)
//    }


    override fun mInit() {
        TODO("Not yet implemented")
    }
}