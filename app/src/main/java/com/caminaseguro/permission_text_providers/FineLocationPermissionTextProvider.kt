package com.caminaseguro.permission_text_providers

class FineLocationPermissionTextProvider: PermissionTextProvider {

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        val normalMessage = "Nuestra app necesita acceder a tu ubicación precisa para poder brindarte infromación más útil"
        val permanentlyDeclinedMessage = "Haz rechazado permanentemente el permiso a tu ubicación precisa, " +
                "puedes otorgarlo en las configuraciones de la app"

        return if (isPermanentlyDeclined) permanentlyDeclinedMessage
        else normalMessage
    }
}