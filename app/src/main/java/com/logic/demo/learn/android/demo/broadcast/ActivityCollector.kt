package com.logic.demo.learn.android.demo.broadcast

import android.app.Activity

object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun add(activity: Activity) {
        activities.add(activity)
    }

    fun remove(activity: Activity) = activities.remove(activity)

    fun finishAll() {
        for (activity in activities) {
            if(!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }

}