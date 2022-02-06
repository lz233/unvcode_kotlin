# 【幼女 Code For Kotlin】好耶

好耶，是[【幼女Code】](https://github.com/RimoChan/unvcode)的 Kotlin 版本！可以用他在 Android 上开发一些奇奇怪怪的东西？~~（比如 Xposed 模块）~~

## 原理和效果

参见原 repo，我才不会再说一遍的（

## 示例

```kotlin
fun main() {
    val result = "Librian幼女娱乐中心开业了，注册即送色图！".unvcode()
    println(result.first)
    println(result.second)
}
```

可选的参数是 `skipAscii: Boolean = true, mse: Double = 0.1`，前者是跳过 ASCII 字符，后者是字符相似度的阈值。

## 输出

```
Librian幼⼥娱乐中⼼开业了，注册即送⾊图！
[-1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.027111192712388904, -1.0, -1.0, -1.0, 0.0, -1.0, -1.0, 0.0, -1.0, -1.0, -1.0, -1.0, -1.0, 0.0, -1.0, -1.0]
```

比原版效果差是正常的，因为这暂时没有指定字体。

## 基准

### Python

```python
import unvcode
import time
t1 = time.time()
for num in range(0,23333):
  s, var = unvcode.unvcode('不许自慰！')
  print(s) 
  print(var)
t2 = time.time()
print((t2-t1)*1000)
```

```
不许⾃慰！
(0.0, None, 0.0, None, None)
... x23333
7420.583009719849
```

### Kotlin

```kotlin
fun main() {
    val t1 = System.currentTimeMillis()
    for (i in 0 until 23333) {
        val result = "不许自慰！".unvcode()
        println(result.first)
        println(result.second)
    }
    val t2 = System.currentTimeMillis()
    println(t2 - t1)
}
```

```
不许⾃慰！
[0.020990749240879125, -1.0, 0.0, -1.0, -1.0]
... x23333
77501
```

比原版慢 10 倍也是正常的，因为 Java 的 `java.awt.image` 历史悠久。~~（反正不是我的问题）~~

## 感谢

[unvcode](https://github.com/RimoChan/unvcode)
