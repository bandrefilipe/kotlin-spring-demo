package demo.client

import demo.client.application.ServerStatsProvider
import demo.client.application.ServerStatsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
private class BeanConfiguration {

    @Bean
    fun serverStatsService(provider: ServerStatsProvider): ServerStatsService = ServerStatsService(provider)
}
