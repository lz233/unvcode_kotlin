# 【幼女 Code For Kotlin】好耶

好耶，是[【幼女Code】](https://github.com/RimoChan/unvcode)的 Kotlin 版本！可以用他在 Android 上开发一些奇奇怪怪的东西？~~（比如 Xposed 模块）~~

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

## 感谢

[unvcode](https://github.com/RimoChan/unvcode)
