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

## 恢复Activity销毁丢失的数据
### onSaveInstanceState
这个方法在Activity被回收之前保证被调用.
### onCreate
```
 override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if(savedInstanceState != null) {
        ///恢复数据
        val tempData = savedInstanceState.getString("data_key")
        Log.d(tag, tempData)
    }
}
```
## Activity的启动模式
启动模式一共有四种,分别是`standard`,`singleTop`,`singleTask`和`singleInstance`,可以在AndroidManifest.xml中通过<activity>标签指定android:launchMode属性来选择启动模式.

### standard
在standard模式下,每当启动一个新的activity,它就会在返回栈中入栈,并处于栈顶的位置.每次启动都会创建一个该Activity的新实例.
### singleTop
在启动Activity时如果发现返回栈的栈顶已经是该Activity,则认为可以直接使用它,不会再创建新的Activity实例.(只有当Activity在栈顶的时候才不会重新创建)

### singleTask
每次启动该Activity时,系统会首先在返回栈中检查是否存在该Activity实例,如果发现已经存在则直接使用该实例,并把在这个Activity之上的所有其他Activity统统出栈,如果没有就会创建一个新的Activity实例.
### singleInstance
Activity会启用一个新的返回栈来管理这个Activity.

## 启动Activity的最佳写法
```
class SecondActivity : AppCompatActivity() {
    ...

    /// 暴露自己的入参
    companion object {
        fun actionStart(context: Context, data1: String, data2: String) {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("param1",data1)
            intent.putExtra("param2",data1)
            context.startActivity(intent)

        }
    }
}
```
## UI
### TextView
```
<TextView
    ///大小和父布局一样大
    android:layout_width="match_parent"
    ///大小刚好包含住里面的内容,也就是由空间内容决定当前控件的大小.
    android:layout_height="wrap_content"
    android:text="Hello World"
    ///对齐方式,可以用 | 来指定多个值
    android:gravity="center"
    //颜色
    android:textColor="#00ff00"
    //大小
    android:textSize="24sp"
    />
```
### Button
```
<Button
    android:id="@+id/button2"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="Second Button 1"
    ///英文可以小写
    android:textAllCaps="false"
    />
```
### EditText
```
<EditText
        android:id="@+id/editText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="HelloWorld"
        android:maxLines="2"
        />
```
### ImageView
```
<ImageView
    android:id="@+id/imageView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@drawable/rem"
    />
```
动态更改`ImageView`的图片
```
imageView.setImageResource(R.drawable.image)
```

### ProgressBar
```
<ProgressBar
//当前进度
        android:progress="50"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        //是否可见
        android:visibility="visible"
        //水平样式
        style="?android:attr/progressBarStyleHorizontal"
        //最大进度
        android:max="100"
        />
```
> visibility: visible可见,invisible不可见,gone不可见,不占用屏幕空间.

## 布局
### LinearLayout
线性布局
```
  <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="100dp">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Button1"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button1"
            android:layout_gravity="bottom"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:text="Button1" />

    </LinearLayout>
```
### RelativeLayout
相对布局,可以通过相对定位的方式让控件出现在布局的任何位置.
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_alignParentTop="true"
        android:text="Button1"
        />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_alignParentTop="true"
        android:text="Button2"
        />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="Button3"
        />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentLeft="true"
        android:layout_alignParentBottom="true"
        android:text="Button4"
        />
    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentRight="true"
        android:layout_alignParentBottom="true"
        android:text="Button5"
        />

</RelativeLayout>
```
相对于某个控件
above可以让一个控件位于另一个控件的上方,below可以让一个控件位于一个控件的下方.
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <Button
        android:id="@+id/button1"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_above="@+id/button3"
        android:layout_toLeftOf="@+id/button3"
        android:text="Button1"
        />
    <Button
        android:id="@+id/button2"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_above="@+id/button3"
        android:layout_toRightOf="@+id/button3"
        android:text="Button2"
        />
    <Button
        android:id="@+id/button3"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:text="Button3"
        />
    <Button
        android:id="@+id/button4"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/button3"
        android:layout_toLeftOf="@+id/button3"
        android:text="Button4"
        />
    <Button
        android:id="@+id/button5"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/button3"
        android:layout_toRightOf="@+id/button3"
        android:text="Button5"
        />

</RelativeLayout>
```
### FrameLayout
帧布局,类似于绝对定位.
```
<?xml version="1.0" encoding="utf-8"?>
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            android:text="Button1"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button1"
            android:layout_gravity="bottom"
            />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:text="Button1" />
</FrameLayout>
```
### 自定义控件
> custom_layout_demo.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

        <Button
            android:id="@+id/customBack"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="BACK"
            />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Title Text"
            android:gravity="center"

            android:layout_weight="1"
            android:layout_gravity="center"
            />

        <Button
            android:id="@+id/customEdit"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center_vertical"
            android:text="Edit" />
</LinearLayout>
```
> CustomLayout.kt

```
class CustomLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    init {
        LayoutInflater.from(context).inflate(R.layout.custom_layout_demo, this)
        customBack.setOnClickListener{
            val activity = context as Activity
            activity.finish()
        }
        customEdit.setOnClickListener {
            Toast.makeText(context, "You clicked Edit button", Toast.LENGTH_SHORT).show()
        }
    }
}
```
> 引入方式

```
<com.logic.demo.learn.android.layout.CustomLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    />
```
### ListView
> ListViewDemo.kt

```
class ListViewDemo : AppCompatActivity() {
    private val data = listOf<String>(
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango",
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view_demo)
        val adapter = FruitAdapter(
            this,
            R.layout.list_item_demo,
            data
        )
        listView.adapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val fruit = data[position]
            Toast.makeText(this, fruit, Toast.LENGTH_SHORT).show()
        }
    }
}
```
> list_item_demo.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:height="50dp"
        android:id="@+id/fruitName"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="center_vertical" />
</LinearLayout>
```
> FruitAdapter.kt

```
class FruitAdapter(activity: Activity, private val resourceId: Int, data: List<String>) :
    ArrayAdapter<String>(activity, resourceId, data) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resourceId, parent, false)
            val fruitName: TextView = view.findViewById(R.id.fruitName)

            viewHolder = ViewHolder(fruitName)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val fruit = getItem(position)
        if (fruit != null) {
            viewHolder.fruitName.text = fruit
        }
        return view
    }

    inner class ViewHolder(val fruitName: TextView)
}
```

### RecyclerView
> recycler_view_demo.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>
```
> RecyclerViewAdapter.kt

```
class RecyclerViewAdapter(private val fruitList: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    ///用于创建ViewHolder实例.
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_demo, parent, false)
            ///横向滚动
    //            .inflate(R.layout.list_item_horizontal, parent, false)

            ///点击事件
            val viewHolder = ViewHolder(view)
            val position = viewHolder.adapterPosition
            val fruit = fruitList[position]
            viewHolder.itemView.setOnClickListener {
                Toast.makeText(parent.context, "You clicked view: $fruit", Toast.LENGTH_SHORT).show()
            }
            viewHolder.fruitName.setOnClickListener {
                Toast.makeText(parent.context, "You clicked fruit name: $fruit", Toast.LENGTH_SHORT).show()
            }
            return viewHolder
        }

    ///告诉RecyclerView一共有多少子项
    override fun getItemCount() = fruitList.size

//    用于对RecyclerView子项的数据进行赋值.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position];
        holder.fruitName.text = fruit;
    }
}
```
> RecyclerViewDemo.kt

```
class RecyclerViewDemo : AppCompatActivity() {
    private val data = listOf<String>(
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango",
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_demo)
        val adapter = RecyclerViewAdapter(
            data
        )
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
```
### 瀑布流
> list_item_staggered_grid.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="10dp">

    <TextView
        android:id="@+id/fruitName"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="left"
        android:layout_marginTop="10dp"
        />
</LinearLayout>
```

> list_view_demo.xml

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <ListView
        android:id="@+id/listView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</LinearLayout>
```

> StaggeredGridAdapter.kt

```
class StaggeredGridAdapter(private val fruitList: List<String>) :
    RecyclerView.Adapter<StaggeredGridAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    ///用于创建ViewHolder实例.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_staggered_grid, parent, false)
        return ViewHolder(view)
    }

    ///告诉RecyclerView一共有多少子项
    override fun getItemCount() = fruitList.size

//    用于对RecyclerView子项的数据进行赋值.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position];
        holder.fruitName.text = fruit;
    }
}
```

> StaggeredGridDemo.kt

```
///瀑布流
class StaggeredGridDemo : AppCompatActivity() {
    private val data = listOf<String>(
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango",
        "Apple",
        "Banana",
        "Orange",
        "Watermelon",
        "Pear",
        "Grape",
        "Pineapple",
        "Cherry",
        "Mango"
    )

    private val fruitList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_demo)
        initFruits()
        val adapter = RecyclerViewAdapter(
            fruitList
        )
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(3) {
            data.forEach { fruitList.add(getRandomLengthString(it)) }
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }
}
```
## Fragment
### Fragment和Activity的交互
> Activity获取Fragment

```
val fragment = leftFrag as LeftFragment
```
> Fragment 获取 Activity

```
if(activity != null) {
    val mainActivity = activity as MainActivity
}
```
### Fragment生命周期
#### onAttach
当Fragment和Activity建立关联时调用
#### onCreateView
为Fragment创建视图时调用
#### onActivityCreated
确保与Fragment相关联的Activity已经创建完毕时调用.
#### onDestroyView
当与Fragment关联的视图被移除时调用
#### onDetach
当Fragment和Activity解除关联时调用.

## 广播
完整的广播系统列表
`<Android SDK>/platforms/<Android api 版本>/data/broadcast_action.txt`
### 标准广播
标准广播是一种完全异步执行的广播,在广播发出之后,所有的BroadcastReceiver几乎会在同一时刻收到这条广播信息,因此他们之间没有任何先后顺序可言.
这种广播的效率会比较高,但同时也意味着它是无法被截断的.
### 有序广播
有序广播则是一种同步执行的广播,在广播发出之后,同一时刻只有一个BroadcastReceiver能够收到这条广播信息,当这个BroadcastReceiver中的逻辑执行完毕之后,广播才会继续传递.
优先级高的BroadcastReceiver就可以先收到广播信息,并且前面的BroadcastReceiver还可以截断正在传递的广播,这样后面的BroadcastReceiver就无法收到广播信息了.
### 注册
注册BroadcastReceiver的方式分为两种:在代码中注册和在Android Manifest.xml中注册.前者称为动态注册,后者称为静态注册.
#### 动态注册
> TimeChangeReceiver.kt

```
class TimeChangeReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Time has changed", Toast.LENGTH_SHORT).show();
    }
}
```

> MainActivity.kt

```
class MainActivity : AppCompatActivity() {
    lateinit var timeChangeReceiver: TimeChangeReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)
   }

   override fun onDestroy() {
       super.onDestroy()
       unregisterReceiver(timeChangeReceiver)
   }
}
```

#### 静态注册
可以让程序在未启动的情况下也能接收广播.
> BootCompleteReceive.kt

```
class BootCompleteReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_SHORT).show();
    }
}
```
> AndroidManifest.xml

```
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

<receiver
    android:name=".learn.android.receiver.BootCompleteReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
```
### 自定义广播
#### 标准广播
> MyBroadcastReceiver.kt

```
class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
```
> MainActivity.kt

```
myBroadcastReceiver.setOnClickListener {
    val intent = Intent("com.logic.demo.MY_BROADCAST")
    intent.setPackage(packageName)
    sendBroadcast(intent)
}
```
> AndroidManifest.xml

```
<receiver
    android:name=".learn.android.receiver.MyBroadcastReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
        <action android:name="com.logic.demo.MY_BROADCAST" />
    </intent-filter>
</receiver>
```
#### 有序广播
是一种同步执行的广播,并且可以被截断.
> AndroidManifest.xml

```
<receiver
    android:name=".learn.android.receiver.MyBroadcastReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter android:priority="100">
        <action android:name="com.logic.demo.MY_BROADCAST" />
    </intent-filter>
</receiver>
<receiver
    android:name=".learn.android.receiver.AnotherBroadcastReceiver"
    android:enabled="true"
    android:exported="true">
    <intent-filter>
        <action android:name="com.logic.demo.MY_BROADCAST" />
    </intent-filter>
</receiver>
```
> MainActivity.kt

```
myBroadcastReceiver.setOnClickListener {
    val intent = Intent("com.logic.demo.MY_BROADCAST")
    intent.setPackage(packageName)
    //发送标准广播
//            sendBroadcast(intent)
    /// 发送有序广播
    sendOrderedBroadcast(intent, null)
}
```
> MyBroadcastReceiver.kt

```
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
        //阻止广播传递
        abortBroadcast()
    }
}
```
> AnotherBroadcastReceiver.kt

```
class AnotherBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "AnotherBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
```
## 数据存储
### 文件存储
context类中提供了一个openFileOutput()方法,可以用于将数据存储到指定的文件夹中.
这个方法有两个参数:第一个是文件名,在文件创建的时候使用,文件名不可以包含路径,所有文件都默认存储到/data/data/<package name>/files/目录下;
第二个参数是文件的操作模式,主要有MODE_PRIVATE和MODE_APPEND两种模式可选,默认是MODE_PRIVATE,表示当指定相同文件名时,所写入的内容将会覆盖源文件中的内容,而MODE_APPEND则表示如果该文件已存在,就往文件里面追加内容.
#### 写入
```
  private fun onSave(intputText: String) {
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(intputText)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
```
#### 读取
```
private fun load(): String {
    val content = StringBuilder()
    try {
        val input = openFileInput("data")
        val reader = BufferedReader(InputStreamReader(input))
        reader.use {
            reader.forEachLine { content.append(it) }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return content.toString()
}
```
### SharedPreferences
文件保存在/data/data/<package name>/shared_prefs/目录下
#### 写入
```
 private fun onSaveSharedPreferences() {
        val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
        editor.putString("name", "Tom")
        editor.putInt("age", 28)
        editor.putBoolean("married", false)
        editor.apply()
    }
```
#### 读取
第二个参数是默认值
```
    private fun onLoadSharedPreferences() {
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        prefs.getString("name", "")
        prefs.getInt("age", 0)
        prefs.getBoolean("married", false)
    }
```
### SQLite数据库存储
数据库文件存放在/data/data/<pageage name>/databases/目录下
#### SQLite创建数据库和增删查改
```
class MyDatabaseHelper(val context: Context, name: String, version:Int) : SQLiteOpenHelper(context, name, null, version) {
    private val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text)"
    override fun onCreate(db: SQLiteDatabase?) {
        //创建数据库
        db?.execSQL(createBook)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_SHORT).show()
    }

    ///更新表,只要传入的version大于之前的version,这个方法就会执行.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //删除数据库
        db?.execSQL("drop table if exists Books")
        db?.execSQL("drop table if exists Category")
        onCreate(db)
    }

    // 插入
    fun insert() {
        val db = writableDatabase
        val value1 = ContentValues().apply {
            put("name", "The Da Vinci Code")
            put("author", "Dan Brown")
            put("pages", 454)
            put("price", 16.96)
        }
        db.insert("Book", null, value1)
        val value2 = ContentValues().apply {
            put("name", "The Lost Symbol")
            put("author", "Dan Brown")
            put("pages", 510)
            put("price", 19.95)
        }
        db.insert("Book", null, value2)
    }

    //更新
    fun update() {
        val db = writableDatabase
        val values = ContentValues()
        values.put("price", 10.99)
        db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
    }

    //删除
    fun delete() {
        val db = writableDatabase
        db.delete("Book", "page > ?", arrayOf("500"))
    }

    //查询
    fun select() {
        val db = writableDatabase
        val cursor = db.query("Book", null,null,null,null,null,null)
        if(cursor.moveToFirst()) {
            do {
                val name = cursor.getString(cursor.getColumnIndex("name"))
                val author = cursor.getString(cursor.getColumnIndex("author"))
                val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                val price = cursor.getDouble(cursor.getColumnIndex("price"))
            } while (cursor.moveToNext())
        }
        cursor.close()
    }
}
```