# Learn Android
## Intent
Intent 是 Android程序中各组件之间进行交互的一种重要方式,它不仅可以指名当前组件想要执行的动作,还可以在不同组件之间传递数据.
Intent一般可用于启动Activity,启动Service以及发送广播等场景.
### 显示Intent
```
button1.setOnClickListener{
    val intent = Intent(this, SecondActivity::class.java)
    startActivity(intent)
}
```
### 隐式Intent
> AndroidManifest.xml

```
<activity android:name=".learn.android.SecondActivity">
    <intent-filter>
        <action android:name="com.logic.activitytest.ACTION_START" />
        <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
</activity>
```
> FistActivity.kt

```
button1.setOnClickListener{
    val intent = Intent("com.logic.activitytest.ACTION_START")
    startActivity(intent)
}
```
每个Intent中只能指定一个action,但能指定多个category.
> AndroidManifest.xml

```
<activity android:name=".learn.android.SecondActivity">
    <intent-filter>
        <action android:name="com.logic.activitytest.ACTION_START" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="com.logic.activitytest.MY_CATEGORY" />
    </intent-filter>
</activity>

...
button1.setOnClickListener {
    //隐式Intent
    val intent = Intent("com.logic.activitytest.ACTION_START")
    intent.addCategory("com.logic.activitytest.MY_CATEGORY")
    startActivity(intent)
}
```
打开网页
> AndroidManifest.xml

```
<activity android:name=".learn.android.ThirdActivity">
    <intent-filter tools:ignore="AppLinkUrlError">
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <data android:scheme="https" />
    </intent-filter>
</activity>
```
> FirstActivity.kt

```
val intent = Intent(Intent.ACTION_VIEW)
intent.data = Uri.parse("https://www.baidu.com")
startActivity(intent)
```
传递参数
> FirstActivity

```
val intent = Intent(this, SecondActivity::class.java)
intent.putExtra("extra_data", "Hello World")
startActivity(intent)
```
> SecondActivity

```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.second_layout)

    val extraData = intent.getStringExtra("extra_data")
    Log.d("SecondActivity", "extra data is $extraData")
}
```

参数返回
> FirstActivity.kt

```
class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first_layout)
        button1.setOnClickListener {
            /*参数返回*/
            val intent = Intent(this, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1 -> if (resultCode == Activity.RESULT_OK) {
                val returnedData = data?.getStringExtra("data_return")
                Log.d("FirstActivity", "returned data is :$returnedData")
            }
        }
    }
}
```
> SecondActivity.kt

```
class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        button2.setOnClickListener{
            val intent = Intent()
            intent.putExtra("data_return", "Hello FirstActivity")
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        val intent = Intent()
        intent.putExtra("data_return", "Hello FirstActivity")
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
```
## Activity 生命周期
### onCreate()
在Activity第一次被创建的时候调用.在这个方法中完成Activity的初始化操作,比如加载布局,绑定事件.
### onStart()
在这个方法在Activity由不可见变为可见的时候的操作.
### onResume()
这个方法在Activity准备好和用户进行交互的时候调用.此时Activity一定位于返回栈的栈顶,并且处于运行状态.
### onPause()
这个方法在系统准备去启动或者恢复另一个Activity的时候调用.在这个方法中将一些消耗CPU的资源释放掉,以及保存一些关键数据.
### onStop()
这个方法在Activity完全不可见的时候调用.如果新Activity是一个对话框式的Activity,那么onPause()方法会执行,onStop()不会
### onDestroy()
这个方法在Activity被销毁之前调用.
### onRester()
这个方法在Activity由停止状态变为运行状态之前调用.