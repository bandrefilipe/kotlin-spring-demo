package demo.client.application

import demo.client.domain.ServerHealth

interface ServerStatsProvider {
    fun health(): ServerHealth
}
