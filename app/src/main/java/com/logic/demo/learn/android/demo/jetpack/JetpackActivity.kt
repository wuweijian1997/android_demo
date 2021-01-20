package com.logic.demo.learn.android.demo.jetpack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.logic.demo.R
import com.logic.demo.learn.android.demo.jetpack.room.AppDatabase
import com.logic.demo.learn.android.demo.jetpack.room.User
import com.logic.demo.learn.android.demo.jetpack.viewmodel.ActivityModel
import com.logic.demo.learn.android.demo.jetpack.viewmodel.ActivityModelFactory
import com.logic.demo.learn.android.demo.jetpack.viewmodel.MyObserver
import com.logic.demo.learn.android.demo.jetpack.workmanage.SimpleWorker
import kotlinx.android.synthetic.main.activity_jetpack.*
import kotlin.concurrent.thread

class JetpackActivity : AppCompatActivity() {
    lateinit var viewModel: ActivityModel
    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jetpack)

        //Lifecycle
        lifecycle.addObserver(MyObserver(lifecycle))
        //
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)
        viewModel =
            ViewModelProvider(this, ActivityModelFactory(countReserved))[ActivityModel::class.java]
        plusOneBtn.setOnClickListener {
            viewModel.plusOne()
        }
        clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this, Observer { count ->
            infoText.text = count.toString()
        })

        getUserBtn.setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this, Observer { user ->
            infoText.text = user.firstName
        })

        /// Room
        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("Tom", "Brady", 40)
        val user2 = User("Tom", "Hanks", 63)
        addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }

        updateDataBtn.setOnClickListener {
            thread {
                user1.age = 42
                userDao.updateUser(user1)
            }
        }

        deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUsersByLastName("Hanks")
            }
        }

        queryDataBtn.setOnClickListener {
            thread {
                for (user in userDao.loadAllUsers()) {
                    Log.d("ViewModelActivity", user.toString())
                }
            }
        }

        /// WorkManager
        doWorkBtn.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                ///延迟执行
//                .setInitialDelay(5, TimeUnit.MINUTES)
                .addTag("simple")
                ///如果request返回了Result.retry(),这个方法可以重新执行任务
//                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()
            WorkManager.getInstance(this).enqueue(request)

            //取消任务,通过标签
            WorkManager.getInstance(this).cancelAllWorkByTag("simple")
            // 取消任务,通过id
            WorkManager.getInstance(this).cancelWorkById(request.id)
            //观察数据变化,也可以通过标签观察所有请求的运行结果
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this, Observer<WorkInfo> { workInfo: WorkInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Log.d("MainActivity", "do work succeeded")
                    } else if (workInfo.state == WorkInfo.State.FAILED) {
                        Log.d("MainActivity", "do work failed")
                    }
                }
                )
        }
    }


    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}