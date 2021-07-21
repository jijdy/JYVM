### java实现jvm虚拟机

* 虚拟的虚拟机，虚拟虚拟机（笑）



#### 命令行操作

* java程序通过命令行进行实现，通过传入的参数对之后程序的运行状态进行判断，例如

  |       指令       |          说明          |
  | :--------------: | :--------------------: |
  |     -version     | 输出版本信息，然后退出 |
  |    -？/-help     | 输出帮助信息，然后退出 |
  |  -cp/-classpath  |     指定用户类路径     |
  | -Dproperty=value |    设置java系统属性    |
  |    -Xms<size>    |    设置堆空间大小，    |
  |    -Xmx<size>    |   设置最大堆空间大小   |
  |    -Xss<size>    |   设置线程栈空间大小   |



#### java搜索class文件

* 在运行用户类路径的用户代码之前，需要进行类加载，所以会按先后顺序搜索下列三个路径的class文件并加载。
* 启动类路径  bootstrap (默认在jre\lib中)
* 扩展类路径  extension (默认在jre\lib\ext)
* 用户类路径  user (默认为当前连接"."，可以使用-cp类追踪更改)
* -cp中使用分隔符加载多个用户类路径，Windows中使用";"分割，Unix中使用":"分割

* **实现方式** ：
  * Entry接口，定义实体接口用于传入的calss路径参数，并通过工厂方法根据参数类型返回需要用到的具体实例
  * CompositeEntry：传入的类路径参数有多个被(;或:)进行分割，则返回该类
  * WildcardEntry：传入的类路径为最后带有*号的泛指
  * ZipEntry：传入的参数用于解析Zip或jar包文件
  * DirEntry：传入的类路径参数为一个类的普通参数
  * Classpath：用于使用-Xjre命令行参数解析启动类和扩展类路径，使用-cp来加载解析用户类路径

#### java解析class文件

* class文件以字节码的形式进行解析，其中主要有uint1，uint2，uint4三种字节长度的数据类型，但需要注意的是Long和Double类型占有8个字节(两个Uint4类型数据)，需要特别注意
* **魔数**：文件中字节码的首部定义，无实际意义，作为标识 class字节码的魔数为0xcafebabe
* **版本号**：分为次版本和主版本，次版本现在以无意义，主版本依照java8的规格使用
* **常量池**：版本号之后为常量池数据，首部为常量池长度，之后为具体常量实现了13种常量，类class，double，field字段，float，interface，long，methodRef，methodType，nameAndType，utf8字符串，String常量，invoke，methodHandle
* **类访问标志位**：之后为类访问标识符，用于标识该该class文件为类还是接口，访问级别为public还是其他等
* **本类名和父类名**：之后两个u2类型分别代表本类类名和父类类名的常量池索引。
* **接口索引**：接口索引类型首先为一个u2类型表示基础的接口数，这后为一个个的u2类型的常量池索引
* **字段和方法表**：之后为字段表，方法表，两者基本相同，以一个索引数组存储数据，有**访问表示符**，**名称的常量池索引**，**描述的常量池索引**，**属性表**，其中属性表也为数组索引存储
* **属性表**：和常量池类似，实现了15种具体属性，其中有为定义属性，SyntheticAttribute类未实现。
* **总结** ：解析class文件时调试过多次，解析其中的所有2进制数据，其类的所有信息都按照**jvm虚拟机规范**有序而繁复的排列，解析class文件的过程，就是将其存储在2进制文件中的内容一步步的拆分，并使用合理的存储结构进行存储和解析，以便之后为进行运算提供数据支持。

