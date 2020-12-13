# Learn kotlin
## 变量和函数
### 变量
val 用来申明不可变的变量,var用来申明可变的变量.
```
val a: Int = 10
var str: String = "Hello World"
```
### 函数
语法规则
```
fun 函数名(入参: 入参类型): 返回值的类型 {
    方法体
}
```
例子(才发现kotlin没有三目运算)
```
fun max(num1: Int, num2: Int): Int {
    return if (num1 > num2) num1
    else num2
}
```
## 程序的逻辑控制
### if
```
fun max(num1: Int, num2: Int): Int {
    var value = 0
    if(num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}
```
> if可以有返回值
```
fun max(num1: Int, num2: Int): Int {
    return if (num1 > num2) num1
    else num2
}
```
> 函数只有一行时,可以省略函数体部分
```
fun max(num1:Int, num2: Int) = if(num1 > num2) num1 else num2
```
### when
```
fun getScore(name: String) = when (name) {
    "Tom" -> 86
    "Jim" -> 77
    "Jack" -> 95
    "Lily" -> 100
    else -> 0
}
```

> 匹配逻辑

```
fun checkNumber(num: Number) {
    when (num) {
        is Int -> println("number is Int")
        is Double -> println("number is Double")
        else -> println("number not support")
    }
}
```
> 不带参数用法
```
fun getScore(name: String) = when {
    name.startsWith("Tom") -> 86
    name == "Jim" -> 77
    name == "Jack" -> 95
    else -> 0
}
```
### 循环语句
创建数组
```
// 创建一个[0, 10]的数组,包含10
val range = 0..10

for( i in 0..10) {
    println(i)
}
```
创建单端闭区间
```
// 创建一个[0, 10) 的数组,不包含10
val range = 0 until 10
```
跳过元素
```
for (i in 0 until 10 step 2) {
    println(i)
}
```
降序数组
```
for (i in 10 downTo1) {
    println(i)
}
```
## 面向对象编程
### 类与对象
创建类
```
class Person {
    var name = ""
    var age = 0
    fun eat() {
        println(name + " is eating. He is " + age + " years old.")
    }
}

val p = Person()
```
### 继承
kotlin默认所有非抽象类都是不可以被继承的.如果一个类需要被继承需要加上open关键字
```
open class Person {
    ...
}

class Student : Person() {
    var sno = ""
    var grade = 0
}
```
### 接口
和Java类似
```
interface Study {
    fun readBooks()

    fun doHomework()
}

class Student : Study {
    override fun readBooks() {
        TODO("Not yet implemented")
    }

    override fun doHomework() {
        TODO("Not yet implemented")
    }
}
```
### 函数可见性修饰符
```
                Java                    Kotlin
public        所有类可见              所有类可见(默认)
private                   当前类可见
protected   当前类,子类,同一包路径下可见         当前类,子类可见
default     同一包路径下的类可见          无
internal        无                   同一模块中的类可见
```
### 数据类与单例类
Kotlin会根据主构造函数中的参数帮你将equals(), hashCode(),toString()等固定且无实际逻辑意义的方法自动生成.
```
data class Cellphone(val brand: String, val price: Double)
```
### 单例类
```
object Singleton {
    fun singletonTest() {
        println("singletonTest is called.")
    }
}

//调用方法
Singleton.singletonTest()
```
## Lambda编程
### 集合和遍历
List
```
// 不可变集合
val list = listOf("Apple", "Banana", "orange", "Pear", "Grape")

// 可变集合
val list = mutableListOf("Apple", "Banana", "orange", "Pear", "Grape")
```
Set
```
// 不可变Set
val set = setOf("Apple", "Banana", "orange", "Pear", "Grape")

// 可变集合
val set = mutableSetOf("Apple", "Banana", "orange", "Pear", "Grape")
```
Map
```
val map = mapOf("Apple" to 1, "Banana" to 2, "orange" to 3, "Pear" to 4, "Grape" to 5)
map["apple"] = 6

for((fruit, number) in map) {
    println("fruit is " + fruit + ", number is " + number)
}
```
### 集合的函数式API
maxBy
```
val list = listOf("Apple", "Banana", "orange", "Pear", "Grape")
val maxLengthFruit = list.maxBy { it. length }

//原型
val lambda = { fruit: String -> fruit.length }
val maxLengthFruit = list.maxBy(lambda)
```
map
```
val list = listOf("Apple", "Banana", "orange", "Pear", "Grape")
val newList = list.map{it.toUpperCase() }
```
filter
```
val list = listOf("Apple", "Banana", "orange", "Pear", "Grape")
val newList = list.filter { it.length <= 5 }
.map{it.toUpperCase() }
```
