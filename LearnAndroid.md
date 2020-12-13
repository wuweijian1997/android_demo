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

```