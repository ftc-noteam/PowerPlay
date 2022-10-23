package asiankoala.ftc2022

object MiyukiState {
    enum class State {
        INTAKING,
        DEPOSITING;
        val next get() = values()[(ordinal + 1) % values().size]
    }

    enum class DepositState {
        GROUND,
        LOW,
        MEDIUM,
        HIGH;
        val next get() = values()[(ordinal + 1) % values().size]
        val prev get() = values()[(ordinal - 1) % values().size]
    }

    fun nextState() {
        state = state.next
    }

    fun incStrat() {
        strategy = strategy.next
    }

    fun decStrat() {
        strategy = strategy.prev
    }

    var reversed = false
    var state = State.INTAKING
    var strategy = DepositState.HIGH
}