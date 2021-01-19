package com.logic.demo.learn.android.demo.thread

import android.os.AsyncTask
import android.widget.Toast
import com.logic.demo.learn.android.extension.showToast
import java.lang.Exception

class DownloadTask : AsyncTask<Unit, Int, Boolean>() {
    //这个方法会在后台任务执行之前调用,用于进行一些界面上的初始化操作,比如显示一个进度条对话框等.
    override fun onPreExecute() {
        //显示进度对话框
//        progressDialog.show();
    }

    override fun doInBackground(vararg params: Unit?): Boolean {
        return try {
            while (true) {
                val downloadProgress = doDownload()
                publishProgress(downloadProgress)
                if (downloadProgress >= 100) {
                    break
                }
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    // demo代码
    private fun doDownload(): Int {
        return 0
    }


    override fun onProgressUpdate(vararg values: Int?) {
//        progressDialog.setMessage("Download ${values[0]}")
    }

    override fun onPostExecute(result: Boolean?) {
        //关闭对话框
//        progressDialog.dismiss()
        if (result == true) {
            "Download succeeded".showToast()
        } else {
            "Download failed".showToast()
        }
    }
}