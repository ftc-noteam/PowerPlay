package asiankoala.ftc2022

enum class Strats {
    GROUND,
    LOW,
    MEDIUM,
    HIGH;

    val inc get() = values()[(ordinal + 1) % values().size]
    val dec get() = values()[(ordinal - 1) % values().size]
}