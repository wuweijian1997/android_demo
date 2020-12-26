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
## 标准函数 with、run和apply
### with
with函数接收两个参数:第一个参数可以是一个任意类型的对象,第二个参数是一个Lambda表达式.with函数会在Lambda表达式中提供第一个参数对象的上下文,并使用Lambda表达式中最后一行代码作为返回值返回.
> example

```
val list = listOf("apple", "banana", "Orange", "Pear", "Grape")
val builder = StringBuilder()
builder.append("Start eating fruits.\n")
for(fruit in list) {
    builder.append(fruit).append("\n")
}
builder.append("Eat all fruits.")
val result = builder.toString()
println(result)
```
> 使用with

```
val list = listOf("apple", "banana", "orange")
val result = with(StringBuilder()) {
    append("Start eating fruits.\n")
    for(fruit in list) {
        append(fruit).append("\n")
    }
    append("Ate all fruits.")
    toString()
}
print(result)
```

### run
run函数是不能直接调用的,而是一定要调用某个对象的run函数才行;其次run函数只接收一个Lambda参数,并且会在Lambda表达式中提供调用对象的上下文.
```
val result = StringBuilder().run {
    append("Start eating fruits.\n")
    for(fruit in list) {
        append(fruit).append("\n")
    }
    append("Ate all fruits.")
    toString()
}
```
### apply
apply函数无法指定返回值,而是会自动返回调用对象本身.
```
val result = StringBuilder().apply {
    append("start eating fruits.")
    for (fruit in list) {
        append(fruit).append("\n")
    }
    append("ate all fruits.")
}
println(result.toString())
```
## Kotlin静态方法
```
class Util {
    fun doAction1() {
        println("do action1")
    }

    companion object {
        fun doAction2() {
            println("do action2")
        }
    }
}
```
## 延迟初始化和密封类
### 对变量延迟初始化
使用关键字 lateinit
```
private lateinit var adapter: MsgAdapter
```
判断是否初始化
```
if(!::adapter.isInitialized) {
    adapter = MsgAdapter(msgList)
}
```
### 密封类
sealed
```
sealed class Result
class Success(val msg: String): Result()
class Failure(val error: Exception): Result()
```
使用
```
fun getResultMsg(result: Result) = when(result) {
    is Success -> result.msg
    is Failure -> "Error is ${result.error.message}"
}
```
## 拓展函数和运算符重载
### 拓展函数
```
fun ClassName.methodName(param1:Int, param2:Int):Int {
    return 0
}
```
demo
```
fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if(char.isLetter()) {
            count++
        }
    }
    return count
}
```
### 运算符重载
```

class Money(val value: Int) {
    operator fun plus(money: Money): Money {
        val sum = value + money.value
        return Money(sum)
    }

    operator fun plus(newValue: Int): Money {
        val sum = value + newValue
        return Money(sum)
    }
}
```
## 高阶函数
定义: 如果一个函数接收另一个函数作为参数,或者返回值的类型是另一个函数,那么该函数就称为高阶函数
```
fun example(func: (String, Int) -> Unit) {
    func("Hello", 123)
}
```
Lambda表达式
```
fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}
val result1 = num1AndNum2(100, 200) {n1, n2 -> n1 + n2}
```
内联函数
使用inline关键字
```
inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    val result = operation(num1, num2)
    return result
}
```