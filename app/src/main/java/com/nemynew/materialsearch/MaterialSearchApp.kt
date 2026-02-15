package com.nemynew.materialsearch

import android.app.Application
import android.content.Intent
import android.util.Log
import kotlin.system.exitProcess

class MaterialSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            val stackTrace = Log.getStackTraceString(throwable)
            
            // Log to Logcat
            Log.e("MaterialSearch", "FATAL EXCEPTION: ${thread.name}\n$stackTrace")
            
            // Launch CrashActivity
            val intent = Intent(this, CrashActivity::class.java).apply {
                putExtra("error_message", throwable.message ?: "Unknown Error")
                putExtra("stack_trace", stackTrace)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }
            startActivity(intent)
            
            // Kill the failing process
            exitProcess(1)
        }
    }
}
