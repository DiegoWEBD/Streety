package com.caminaseguro.permission_text_providers

class PostNotificationsPermissionTextProvider: PermissionTextProvider {

    override fun getDescription(isPermanentlyDeclined: Boolean): String {
        val normalMessage = "Necesitas otorgar permisos a nuestra app para poder enviarte notificaciones sobre" +
                " lo que ocurre en tu entorno o con tus amigos"
        val permanentlyDeclinedMessage = "Haz rechazado permanentemente el permiso a recibir notificaciones, " +
                "puedes otorgarlo en las configuraciones de la app"

        return if (isPermanentlyDeclined) permanentlyDeclinedMessage
        else normalMessage
    }
}