# Characterview

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.ruijun/characterview/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.ruijun/characterview/)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)

在[RoundedLetterView](https://github.com/ruijun/RoundedLetterView)上进行扩展改造。  
根据名字显示字符的View，类似Android5.0, Gmail风格联系人头像

V1.0
========
* 支持圆形和矩形View
* 支持边框显示
* 支持定义显示字符的数目

Gradle
========
```groovy
dependencies {
   compile 'com.github.ruijun:characterview:1.0'
}
```

Maven
========
```
<dependency>
    <groupId>com.github.ruijun</groupId>
    <artifactId>characterview</artifactId>
    <version>1.0</version>
</dependency>

```

JitPack
========
step1: Add it in your root build.gradle at the end of repositories:  
```
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
 ```
step2: Add the dependency  
```
dependencies {
	        compile 'com.github.ruijun:Characterview:-SNAPSHOT'
	}	
```

Screenshot  
========
![矩形](/Screenshots/screenshot1.png) ![圆形](/Screenshots/screenshot2.png) ![圆形带边框](/Screenshots/screenshot3.png)  

Thanks 
========
[RoundedLetterView](https://github.com/ruijun/RoundedLetterView) 

