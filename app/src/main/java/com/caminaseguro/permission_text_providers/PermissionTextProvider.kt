package com.caminaseguro.permission_text_providers

interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}