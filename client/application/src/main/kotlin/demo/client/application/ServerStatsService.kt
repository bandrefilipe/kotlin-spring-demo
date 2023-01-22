package demo.client.application

import mu.KotlinLogging

class ServerStatsService(
    private val provider: ServerStatsProvider,
) {

    init {
        this.logServerHealth()
    }

    private fun logServerHealth() {
        val health = provider.health()
        log.info { "Server health is ${health.status}" }
    }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
