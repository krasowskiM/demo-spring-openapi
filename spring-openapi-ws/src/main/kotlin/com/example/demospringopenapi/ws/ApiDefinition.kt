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

    @GetMapping
    fun getAllVehicles() = vehicleService.getAll()

    @PutMapping
    fun saveVehicle(@RequestBody vehicle: Vehicle) {
        vehicleService.save(vehicle)
    }

    @DeleteMapping("/{id}")
    fun deleteVehicle(@PathVariable id: String) {
        vehicleService.delete(id)
    }
}

@Service
class VehicleService(private val vehicleRepository: VehicleRepository) {

    fun getVehicleById(id: String) = vehicleRepository.get(id)
    fun save(vehicle: Vehicle) = vehicleRepository.store(vehicle)
    fun getAll(): Set<Vehicle> = vehicleRepository.getAll()
    fun delete(id: String) {
        vehicleRepository.delete(id)
    }
}

@Component
class VehicleRepository {
    private val vehicleStorage: MutableMap<String, Vehicle> = mutableMapOf()

    fun get(id: String) = vehicleStorage[id]
    fun store(vehicle: Vehicle) = vehicleStorage + mapOf(vehicle.id to vehicle)
    fun getAll(): Set<Vehicle> = vehicleStorage.values.toSet()
    fun delete(id: String) {
        vehicleStorage.remove(id)
    }
}

data class Vehicle(
        val id: String
)