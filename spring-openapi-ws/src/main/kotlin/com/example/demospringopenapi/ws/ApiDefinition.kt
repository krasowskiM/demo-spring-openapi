package com.example.demospringopenapi.ws

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

private var vehicleIdCounter = 1

@OpenAPIDefinition(
    info = Info(title = "vehicle API", description = "api for vehicle data processing", version = "1")
)
@RestController
@RequestMapping("/api/vehicle")
class VehicleController(private val vehicleService: VehicleService) {

    @GetMapping("/{id}")
    fun getVehicle(@PathVariable id: Int) = ResponseEntity.ok().body(vehicleService.getVehicleById(id))

    @Operation(
        description = "get all vehicles",
        responses = [
            ApiResponse(responseCode = "200", description = "get all vehicles when no issues ocurred"),
        ]
    )
    @GetMapping
    fun getAllVehicles() = vehicleService.getAll()

    @Operation(
        description = "save a vehicle",
        responses = [
            ApiResponse(responseCode = "200", description = "vehicle saved successfully"),
            ApiResponse(responseCode = "503", description = "service unavailable when queue processing in effect")
        ]
    )
    @PutMapping
    fun saveVehicle(@RequestBody vehicle: Vehicle) {
        vehicleService.save(vehicle)
    }

    @DeleteMapping("/{id}")
    fun deleteVehicle(@PathVariable id: Int) {
        vehicleService.delete(id)
    }
}

@Service
class VehicleService(private val vehicleRepository: VehicleRepository) {

    fun getVehicleById(id: Int) = vehicleRepository.get(id)
    fun save(vehicle: Vehicle) = vehicleRepository.store(vehicle)
    fun getAll(): Set<Vehicle> = vehicleRepository.getAll()
    fun delete(id: Int) {
        vehicleRepository.delete(id)
    }
}

@Component
class VehicleRepository {
    private val vehicleStorage: MutableMap<Int, Vehicle> = mutableMapOf()

    fun get(id: Int) = vehicleStorage[id]
    fun store(vehicle: Vehicle) {
        vehicle.apply { id = initId() }
        vehicleStorage[vehicle.id] = vehicle
    }

    fun getAll(): Set<Vehicle> = vehicleStorage.values.toSet()
    fun delete(id: Int) {
        vehicleStorage.remove(id)
    }
}

data class Vehicle(
    var id: Int,
    val registrationNumber: String?
)

fun initId(): Int {
    return vehicleIdCounter++
}
