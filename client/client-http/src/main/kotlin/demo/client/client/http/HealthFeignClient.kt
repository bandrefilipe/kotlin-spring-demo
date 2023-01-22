package demo.client.client.http

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "health-checker", url = "http://localhost:8088/health")
internal interface HealthFeignClient {

    @GetMapping
    fun getHealth(): ResponseEntity<Map<String, Any?>>
}
