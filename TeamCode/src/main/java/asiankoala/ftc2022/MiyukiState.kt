package asiankoala.ftc2022

object MiyukiState {
    enum class State {
        INTAKING,
        DRIVING_TO_DEPOSIT,
        PRE_DEPOSIT,
        DEPOSITING,
        POST_DEPOSIT;

        val inc get() = values()[(ordinal + 1) % values().size]
        val dec get() = values()[(ordinal - 1) % values().size]
    }

    enum class DepositState {
        GROUND,
        LOW,
        MEDIUM,
        HIGH;

        val inc get() = values()[(ordinal + 1) % values().size]
        val dec get() = values()[(ordinal - 1) % values().size]
    }

    var reversed = false
    var state = State.DRIVING_TO_DEPOSIT
    var strategy = DepositState.HIGH
}