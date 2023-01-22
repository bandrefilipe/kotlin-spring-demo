package demo.client.client.http

import demo.client.application.ServerStatsProvider
import demo.client.domain.HealthStatus
import demo.client.domain.ServerHealth
import mu.KotlinLogging
import org.springframework.stereotype.Component

@Component
private class ServerStatsProviderAdapter(
    private val healthClient: HealthFeignClient,
): ServerStatsProvider {

    override fun health(): ServerHealth = healthClient.getHealth()
        .also { response -> log.debug { "Received health check: $response" } }
        .body
        ?.get("status")
        ?.toString()
        .let { status -> HealthStatus.from(status) }
        .let { healthStatus -> ServerHealth(healthStatus) }

    companion object {
        private val log = KotlinLogging.logger {}
    }
}
