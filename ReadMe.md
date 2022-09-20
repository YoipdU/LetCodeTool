## 简单的LetCode本地辅助

只需要实现LetCode接口的两个方法，就可以轻松测试题目。

### 介绍

平常在刷题的时候，将LetCode题目复制到本地进行书写与测试的时候，总需要为每一个题目创建main方法，这样很麻烦。

后来创建了一个LetCode接口和Main类，所有的题目类都成为LectCode接口的实现类，这样就可以将很多个题目的main方法改为LetCode接口的test方法，并集中在Main类调用，利用多态的特性，只需要将实例修改为需要测试的实现类就可以测试了。

但是这也比较麻烦，每次做新的题目就需要重新打开Main类修改实例对象。

最后利用反射获取LetCode所有的实现类，LetCode接口还添加了*needTest*方法来确定哪个实现类需要测试。

### 使用

从LetCode网站复制下来的模板代码需要实现LetCodeTool包下的LetCode接口。LetCode接口有两个方法，*needTest*和*test*。

test方法用于编写测试。

needTest方法是控制是否需要执行测试，true为需要测试，false为不需要测试。

程序运行需要的Main方法在LetCodeTool包的Main类里。

### 文件阐述

- LetCode接口

  测试和决定是否测试的接口。

- ClassUtil类

  通过反射获取一个接口的所有实现类，调用这个类的*getAllClassByInterface(Class in)*方法即可获得接口的所有实现类。

- Main类

  含有Main方法，程序的入口。Main方法来遍历所有实现类，并判断是否需要执行测试。

### 参考

1. [Object.requireNonNull 方法说明](https://www.jianshu.com/p/e8d33f57373c)
2. [java获取class路径_JAVA获取CLASSPATH路径的方法详解](https://blog.csdn.net/weixin_35659653/article/details/114038647)
3. [获取Java接口的所有实现类](https://www.cnblogs.com/wangzhen-fly/p/11002814.html)
4. [Java-深入理解ServiceLoader类与SPI机制](https://blog.csdn.net/li_xunhuan/article/details/103017286)
5. [获得class对象的三种方法](https://blog.csdn.net/mocas_wang/article/details/107428506)