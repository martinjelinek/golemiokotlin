package cz.vse.golemiokotlinlib.common.entity.featurescollection

import cz.vse.golemiokotlinlib.common.entity.serializers.AQHourlyIndexSerializer
import cz.vse.golemiokotlinlib.common.entity.serializers.AQObject
import cz.vse.golemiokotlinlib.common.entity.serializers.IdObject
import cz.vse.golemiokotlinlib.common.entity.serializers.IdObjectSerializer
import cz.vse.golemiokotlinlib.common.entity.serializers.Image
import cz.vse.golemiokotlinlib.common.entity.serializers.ImageSerializer
import cz.vse.golemiokotlinlib.common.entity.serializers.OpeningHour
import cz.vse.golemiokotlinlib.common.entity.serializers.OpeningHourPolymorphic
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
abstract class Properties

@Serializable
data class AirQualityStationsProperties(
    val id: String,
    val name: String,
    val district: String? = null,
    val measurement: Measurement? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
) : Properties() {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AirQualityStationsProperties) return false

        if (id != other.id) return false
        if (name != other.name) return false
        if (district != other.district) return false
        if (measurement != other.measurement) return false
        return updatedAt == other.updatedAt
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + (district?.hashCode() ?: 0)
        result = 31 * result + (measurement?.hashCode() ?: 0)
        result = 31 * result + (updatedAt?.hashCode() ?: 0)
        return result
    }
}

@Serializable
data class Measurement(
    @SerialName("AQ_hourly_index")
    @Serializable(with = AQHourlyIndexSerializer::class)
    val aqHourlyIndex: AQObject? = null,
    val components: List<Component>? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Measurement) return false

        if (aqHourlyIndex != other.aqHourlyIndex) return false
        return components == other.components
    }

    override fun hashCode(): Int {
        var result = aqHourlyIndex?.hashCode() ?: 0
        result = 31 * result + (components?.hashCode() ?: 0)
        return result
    }
}

@Serializable
data class Component(
    @SerialName("averaged_time")
    val averagedTime: AveragedTime? = null,
    val type: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Component) return false

        if (averagedTime != other.averagedTime) return false
        return type == other.type
    }

    override fun hashCode(): Int {
        var result = averagedTime?.hashCode() ?: 0
        result = 31 * result + (type?.hashCode() ?: 0)
        return result
    }
}

@Serializable
data class AveragedTime(
    @SerialName("averaged_hours")
    val averagedHours: Int? = null,
    val value: Double? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is AveragedTime) return false

        if (averagedHours != other.averagedHours) return false
        return value == other.value
    }

    override fun hashCode(): Int {
        var result = averagedHours ?: 0
        result = 31 * result + (value?.hashCode() ?: 0)
        return result
    }
}

@Serializable
data class GardenProperties(
    val id: String,
    val name: String,
    val address: Address? = null,
    val description: String? = null,
    val district: String? = null,
    @Serializable(with = ImageSerializer::class)
    val image: Image? = null,
    val properties: List<Property>? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val url: String? = null
) : Properties()

@Serializable
data class Property(
    val id: String,
    val description: String? = null,
    val value: String? = null
)

@Serializable
data class BicycleCounterProperties(
    val id: String,
    val name: String,
    val route: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val directions: List<Direction>? = null
) : Properties()

@Serializable
data class Direction(
    val id: String? = null,
    val name: String? = null
)

@Serializable
data class CityDistrictsProperties(
    val id: Int,
    val name: String,
    val slug: String,
    @SerialName("updated_at")
    val updatedAt: String? = null
) : Properties()

@Serializable
data class MedicalInstitutionProperties(
    val id: String,
    val name: String,
    val address: Address? = null,
    val district: String? = null,
    val email: List<String>? = null,
    @SerialName("institution_code")
    val institutionCode: String,
    @SerialName("pharmacy_code")
    val pharmacyCode: String? = null,
    @SerialName("opening_hours")
    val openingHours: List<OpeningHourPolymorphic>? = null,
    val telephone: List<String>? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val web: List<String>? = null,
    val type: PharmacyType? = null
) : Properties()

@Serializable
data class PharmacyType(
    val description: String? = null,
    val group: String? = null,
    val id: String? = null
)

@Serializable
data class MunicipalityAuthorityProperties(
    val id: String,
    val name: String,
    val image: Image? = null,
    val email: List<String>? = null,
    val telephone: List<String>? = null,
    val web: List<String>? = null,
    val address: Address? = null,
    val agendas: List<Agenda>? = null,
    val district: String? = null,
    @SerialName("official_board")
    val officialBoard: String? = null,
    @SerialName("opening_hours")
    val openingHours: List<OpeningHour>? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val type: MunicipalityType? = null
) : Properties()

@Serializable
data class Agenda(
    val keywords: List<String>? = null,
    val description: String? = null,
    @SerialName("long_description")
    val longDescription: String? = null
)

@Serializable
data class MunicipalityType(
    val description: String? = null,
    val id: String? = null
)

@Serializable
data class MunicipalLibraryProperties(
    val id: Int? = null,
    val name: String? = null,
    val address: Address? = null,
    val email: String? = null,
    @SerialName("opening_hours")
    val openingHours: List<OpeningHourPolymorphic>? = null,
    val services: List<MunicipalLibraryService>? = null,
    val telephone: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val web: String? = null,
    val district: String? = null
) : Properties()

@Serializable
data class MunicipalLibraryService(
    val description: String? = null,
    val id: Int? = null,
    val name: String? = null
)

@Serializable
data class MunicipalPoliceStationProperties(
    val id: String,
    @SerialName("cadestral_area")
    val cadestralArea: String? = null,
    val note: String,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val district: String? = null,
    val address: Address
) : Properties()

@Serializable
data class ParkingV1Properties(
    @Serializable(with = IdObjectSerializer::class)
    @SerialName("id") val id: IdObject? = null,
    val name: String,
    @SerialName("parking_type")
    val parkingType: ParkingType? = null,
    @SerialName("num_of_free_places")
    val numOfFreePlaces: Int,
    @SerialName("num_of_taken_places"
    ) val numOfTakenPlaces: Int,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("total_num_of_places")
    val totalNumOfPlaces: Int?,
    @SerialName("average_occupancy")
    val averageOccupancy: Map<String, Map<String, Int>>? = null,
    val district: String? = null,
    val address: Address? = null,
    @SerialName("last_updated")
    val lastUpdated: Long? = null,
    @SerialName("payment_link")
    val paymentLink: String? = null,
    @SerialName("payment_shortname")
    val paymentShortname: String? = null
) : Properties()

@Serializable
data class ParkingType(
    val id: Int? = null,
    val description: String? = null
)

@Serializable
data class PlaygroundProperties(
    val id: Int,
    val name: String,
    val image: Image? = null,
    val content: String,
    val perex: String? = null,
    val properties: List<PlaygroundPropertyDetail>? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    val url: String,
    val district: String? = null,
    val address: Address? = null
) : Properties()

@Serializable
data class PlaygroundPropertyDetail(
    val id: Int? = null,
    val description: String? = null
)

@Serializable
data class WasteCollectionProperties(
    val id: String,
    val name: String,
    val accessibility: Accessibility? = null,
    val containers: List<Container>? = null,
    val district: String? = null,
    @SerialName("station_number")
    val stationNumber: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null,
    @SerialName("is_monitored")
    val isMonitored: Boolean? = null,
    @SerialName("ksnko_id")
    val ksnkoId: Int? = null
) : Properties()

@Serializable
data class Accessibility(
    val description: String,
    val id: Int
)

@Serializable
data class Container(
    @SerialName("cleaning_frequency")
    val cleaningFrequency: CleaningFrequency? = null,
    @SerialName("container_type")
    val containerType: String? = null,
    val description: String? = null,
    @SerialName("trash_type")
    val trashType: TrashType? = null,
    @SerialName("last_measurement")
    val lastMeasurement: LastMeasurement? = null,
    @SerialName("last_pick")
    val lastPick: String? = null,
    @SerialName("ksnko_id")
    val ksnkoId: Int? = null,
    @SerialName("container_id")
    val containerId: String? = null,
    @SerialName("sensor_code")
    val sensorCode: String? = null,
    @SerialName("sensor_supplier")
    val sensorSupplier: String? = null,
    @SerialName("sensor_id")
    val sensorId: String? = null,
    @SerialName("is_monitored")
    val isMonitored: Boolean? = null
)

@Serializable
data class CleaningFrequency(
    val id: Int? = null,
    val duration: String? = null,
    val frequency: Int? = null,
    @SerialName("pick_days")
    val pickDays: String? = null,
    @SerialName("next_pick")
    val nextPick: String? = null
)

@Serializable
data class TrashType(
    val description: String? = null,
    val id: Int? = null
)

@Serializable
data class LastMeasurement(
    @SerialName("measured_at_utc")
    val measuredAtUtc: String? = null,

    @SerialName("percent_calculated")
    val percentCalculated: Int? = null,

    @SerialName("prediction_utc")
    val predictionUtc: String? = null
)

@Serializable
data class WasteYardProperties(
    val id: String? = null,
    val name: String? = null,
    val properties: List<PropertyItem>?,
    val address: Address?,
    val contact: String?,
    @SerialName("operating_hours") val operatingHours: String?,
    val operator: String?,
    @SerialName("updated_at") val updatedAt: String?,
    val type: String?,
    val district: String?
) : Properties()

@Serializable
data class PropertyItem(
    val id: String?,
    val description: String?,
    val value: String?
)

@Serializable
data class Address(
    @SerialName("address_country")
    val addressCountry: String? = null,

    @SerialName("address_formatted")
    val addressFormatted: String? = null,

    @SerialName("address_locality")
    val addressLocality: String? = null,

    @SerialName("address_region")
    val addressRegion: String? = null,

    @SerialName("postal_code")
    val postalCode: String? = null,

    @SerialName("street_address")
    val streetAddress: String? = null
)
