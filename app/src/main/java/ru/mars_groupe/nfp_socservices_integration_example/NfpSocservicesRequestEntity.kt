package ru.mars_groupe.nfp_socservices_integration_example

data class NfpSocservicesRequestEntity(
    val orgCode: String?, // nfpcore.org_legal_person_employees.owner_code
    val employeeCode: String, // nfpcore.org_legal_persons.owner_code
    val control_services: Boolean,
    val certificate_id: String?, // nfpcore.crt_certificates.cert_number
    val person_id: String?, // nfpcore.psn_person_identifiers.owner_soc_id
    val services: List<NfpSocservicesServiceIdEntity>
)