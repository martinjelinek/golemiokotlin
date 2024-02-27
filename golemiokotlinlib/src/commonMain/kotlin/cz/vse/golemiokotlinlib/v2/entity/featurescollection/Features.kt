package cz.vse.golemiokotlinlib.v2.entity.featurescollection

import cz.vse.golemiokotlinlib.v2.entity.serializers.Geometry
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable

@Serializable
internal data class FeatureCollection<T : Feature>(
    val type: String? = null,
    val features: List<T>? = null,
)

@Polymorphic
@Serializable
sealed class Feature {
    val geometry: Geometry? = null
    abstract val properties: Properties?
    val type: String? = null
}

@Serializable
data class AirQualityStation(
    override val properties: AirQualityStationsProperties? = null
) : Feature()

@Serializable
data class BicycleCounter(
    override val properties: BicycleCounterProperties? = null
) : Feature()

@Serializable
data class CityDistrict(
    override val properties: CityDistrictsProperties? = null
) : Feature()

@Serializable
data class Garden(
    override val properties: GardenProperties? = null
) : Feature()

@Serializable
data class MedicalInstitution(
    override val properties: MedicalInstitutionProperties? = null
) : Feature()

@Serializable
data class MunicipalAuthority(
    override val properties: MunicipalityAuthorityProperties? = null
) : Feature()

@Serializable
data class MunicipalLibrary(
    override val properties: MunicipalLibraryProperties? = null
) : Feature()

@Serializable
data class MunicipalPoliceStation(
    override val properties: MunicipalPoliceStationProperties? = null
) : Feature()

@Serializable
data class ParkingV1(
    override val properties: ParkingV1Properties? = null
) : Feature()

@Serializable
data class ParkingV2(
    override val properties: ParkingV2Properties? = null
) : Feature()

@Serializable
data class Playground(
    override val properties: PlaygroundProperties? = null
) : Feature()

@Serializable
data class WasteCollection(
    override val properties: WasteCollectionProperties? = null
) : Feature()

@Serializable
data class WasteYard(
    override val properties: WasteYardProperties? = null
) : Feature()
