package com.logic.demo.project.step.frame

import android.app.Activity
import android.app.Application
import android.os.Process.killProcess
import android.os.Process.myPid
import java.util.*
import kotlin.system.exitProcess

class FrameApplication:Application() {
    companion object{
        private val activityList: LinkedList<Activity> = LinkedList<Activity>()
        private lateinit var instance: FrameApplication
        fun getActivityList() = activityList
        fun addActivity(activity: Activity) {
            activityList.add(activity)
        }

        fun removeActivity(activity: Activity) {
            activityList.remove(activity)
        }

        private fun clearActivity() {
            activityList.forEach {
                it.finish()
            }
        }

        fun exitApp() {
            try {
                clearActivity()
            } catch (e: Exception) {
            } finally {
                exitProcess(0)
                killProcess(myPid())
            }
        }
    }
    private lateinit var prefsManager: PrefsManager
    fun getPrefsManager() = prefsManager

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefsManager= PrefsManager(this)
    }
}