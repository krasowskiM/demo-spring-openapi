package com.example.demospringopenapi.ws

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/vehicle")
class VehicleController(private val vehicleService: VehicleService) {
    @GetMapping("/{id}")
    fun getVehicle(@PathVariable id: String) = ResponseEntity.ok().body(vehicleService.getVehicleById(id))

    @PostMapping
    fun saveVehicle(@RequestBody vehicle: Vehicle): ResponseEntity<Any> {
        vehicleService.save(vehicle)
        return ResponseEntity.ok().build()
    }
}

@Service
class VehicleService(private val vehicleRepository: VehicleRepository) {

    fun getVehicleById(id: String) = vehicleRepository.get(id)

    fun save(vehicle: Vehicle) = vehicleRepository.store(vehicle)
}

@Component
class VehicleRepository {
    private val vehicleStorage: Map<String, Vehicle> = mutableMapOf()

    fun get(id: String) = vehicleStorage[id]

    fun store(vehicle: Vehicle) = vehicleStorage + mapOf(vehicle.id to vehicle)
}

data class Vehicle(
        val id: String
)