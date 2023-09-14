package ru.mars_groupe.nfp_socservices_integration_example

enum class NfpResponseCode(val code: Int) {
    SUCCESS(0),
    INVALID_ARGS(400),
    UNAUTHORIZED(401),
    ACCESS_DENIED(403),
    NOT_AVAILABLE(404)
}