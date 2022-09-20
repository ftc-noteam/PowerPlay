package asiankoala.ftc2022

import com.asiankoala.koawalib.math.Vector

// viewing from the audience perspective
// top left / bottom right terminal is red
// bottom left / top right terminal is blue
// center of field is (0,0)
// in other words, roadrunner coordinate field
object FieldConstants {
    val V1Ground = Vector(-48, -48)
    val V2Low = Vector(-48, -24)
    val V3Ground = Vector(-48, 0)
    val V4Low = Vector(-48, 24)
    val V5Ground = Vector(-48, 48)

    val W1Low = Vector(-24, -48)
    val W2Med = Vector(-24, -24)
    val W3High = Vector(-24, 0)
    val W4Medium = Vector(-24, 24)
    val W5Low = Vector(-24, 48)

    val X1Ground = Vector(0, -48)
    val X2High = Vector(0, -24)
    val X3Ground = Vector(0, 0)
    val X4High = Vector(0, 24)
    val X5Ground = Vector(0, 48)

    val Y1Low = Vector(24, -48)
    val Y2Med = Vector(24, -24)
    val Y3High = Vector(24, 0)
    val Y4Med = Vector(24, 24)
    val Y5Low = Vector(24, 48)

    val Z1Ground = Vector(48, -48)
    val Z2Low = Vector(48, -24)
    val Z3Ground = Vector(48, 0)
    val Z4Low = Vector(48, 24)
    val Z5Low = Vector(48, 48)

    val closeBlueAutoStack = Vector(-12, -70)
    val farBlueAutoStack = Vector(-12, 70)
    val closeRedAutoStack = Vector(12, -70)
    val farRedAutoStack = Vector(12, 70)

    val closeBlueSignal = Vector(-36, -36)
    val farBlueSignal = Vector(-36, 36)
    val closeRedSignal = Vector(36, -36)
    val farRedSignal = Vector(36, 36)

    const val lowHeight = 13.5
    const val mediumHeight = 23.5
    const val highHeight = 33.5
}