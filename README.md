<br/>
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)&ensp;
[![](https://img.shields.io/badge/platform-android-green)](https://github.com/loperSeven)&ensp;
[![](https://img.shields.io/badge/license-MIT-blue)](https://opensource.org/licenses/MIT)
<br/>

基于实际项目分离出来的一个库，主要为了方便实现一些穿孔效果的卡片

可以轻松实现 小票、电影票、机票、分享卡片等等效果

<br/>

## 预览

## 快速体验

<br/>
或者&ensp;[点击下载](http://fir.cqtencent.cn/dtpicker)
<br/>

## 如何引入
Step 1. 添加 JitPack repository
```
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```
Step 2. 添加 Gradle依赖
```
dependencies {
    ...
    implementation 'com.google.android.material:material:1.1.0' //为了防止不必要的依赖冲突，0.0.3开始需要自行依赖google material库
    implementation 'com.github.loperSeven:DateTimePicker:0.5.4'//具体版本请看顶部jitpack标识，如0.5.4,仅支持androidx
}


```
## 如何使用

## 联系我
Issues：[Issues](https://github.com/loperSeven/DateTimePicker/issues)
<br/>
邮箱：ctop007@163.com
<br/>

## Licenses
```
MIT License

Copyright (c) 2022 caopu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

