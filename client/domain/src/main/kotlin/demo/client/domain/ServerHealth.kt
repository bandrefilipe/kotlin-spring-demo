package demo.client.domain

import mu.KotlinLogging

data class ServerHealth(
    val status: HealthStatus = HealthStatus.UNKNOWN,
)

enum class HealthStatus {
    UP,
    DOWN,
    OUT_OF_SERVICE,
    UNKNOWN;

    companion object {
        private val log = KotlinLogging.logger {}

        fun from(value: String?): HealthStatus = when (value) {
            null -> UNKNOWN
            "UP" -> UP
            "DOWN" -> DOWN
            "OUT_OF_SERVICE" -> OUT_OF_SERVICE
            "UNKNOWN" -> UNKNOWN
            else -> {
                log.warn { "Tried to map an unmapped HealthStatus $value" }
                UNKNOWN
            }
        }
    }
}
