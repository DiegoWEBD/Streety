package com.caminaseguro.permission_text_providers

class CoarseLocationPermissionTextProvider: PermissionTextProvider {

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        val normalMessage = "Nuestra app necesita acceder a tu ubicación aproximada para poder brindarte infromación sobre tu entorno"
        val permanentlyDeclinedMessage = "Haz rechazado permanentemente el permiso a tu ubicación aproximada, " +
                "puedes otorgarlo en las configuraciones de la app"

        return if (isPermanentlyDeclined) permanentlyDeclinedMessage
        else normalMessage
    }
}