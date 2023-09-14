package ru.mars_groupe.nfp_socservices_integration_example

data class NfpSocservicesRequestEntity(
    val orgCode: String?,
    val employeeCode: String,
    val control_services: Boolean,
    val certificate_id: String?,
    val person_id: String?,
    val services: List<NfpSocservicesServiceIdEntity>
)