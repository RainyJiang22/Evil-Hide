package me.sweetll.evilhide.model


import android.content.pm.ApplicationInfo
import me.sweetll.evilhide.AppApplication
import me.sweetll.evilhide.extension.*
import me.sweetll.evilhide.service.HiddenService

class AppInfo(val applicationInfo: ApplicationInfo) {
    val packageName: String
        get() = applicationInfo.packageName

    var favorite: Boolean
        get() = packageName.getFavorite()
        set(value) {
            packageName.saveFavorite(value)
        }
    var hidden: Boolean
        get() = !applicationInfo.enabled
        set(value) {
            val cmd = "pm ${if (value) "disable" else "enable"} $packageName"
            HiddenService.performAction(cmd)
            applicationInfo.enabled = !applicationInfo.enabled
        }
    var password: String
        get() = packageName.getPassword()
        set(value) {
            packageName.savePassword(value)
        }
}