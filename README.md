# 概述
自学《深入理解Java虚拟机》和《Java虚拟机规范》之后，自己对 `JVM` 实现的尝试。

目标计划：
- 实现 `classpath` 的设置
- 实现对 `.class` 文件的解析和处理
- 实现对 `method` 字段中 `code` 属性的执行，模拟 JVM 执行引擎

系统类图：
-
![](http://on-img.com/chart_image/5aacfabfe4b0262b8b3f52d6.png)

- `ClassFileLoader` 作为系统的入口，负责设置 `classpath` 以及装载字节码文件。
- `ClassFileAnalyser` 负责读取并解析字节码文件。
- `ByteCodeIterator` 采用迭代器模式，将字节码文件传入进行初始化之后，对外界屏蔽底层字节操作，返回特定长度的整型或者字符串。
- `ClassFile` 包含 `ConstantPool`、`AccessFlag`、`FieldInfo`、`MethodInfo` 等属性，其中 `ConstantPool` 和 `Attribute` 依据类型的不同，由工厂类进行创建。
- `ByteCodeParse` 使用命令模式，将 JVM 执行引擎可识别的字节码命令抽象为 `ByteCodeCmd` 接口的实现类，并再次抽象出 `NoOperandCmd`、`OneOperandCmd`和`TwoOperandCmd`。

# 1. class类文件结构
Class文件是一组以8字节为基础单位的二进制流，各个数据项目严格按照顺序紧凑地排列在Class文件中，中间没有任何分隔符。

- 无符号数属于基本的数据类型，以`u1`、`u2`、`u4`、`u8`来分别代表1个字节、2个字节、4个字节、8个字节的无符号数，无符号数可以用来描述数字、索引引用、数量值或者按照UTF-8编码组成的字符串值。
- 表是有多个无符号数或者其他表作为数据项构成的复合数据结构，所有表都习惯性的以`_info`结尾。表用于描述有层次关系的复合结构的数据。

### <center>Class文件格式</center>

<center>

类型 | 名称 | 数量
:-: | :-: | :-:
u4 | magic | 1
u2 | minor_version | 1
u2 | major_version | 1
u2 | constant_pool_count | 1
cp_info | constant_pool | constant_pool_count - 1
u2 | access_flags | 1
u2 | this_class | 1
u2 | super_class | 1
u2 | interfaces_count | 1
u2 | interfaces | interfaces_count
u2 | fields_count | 1
field_info | fields | fields_count
u2 | methods_count | 1
method_info | methods | methods_count
u2 | attributes_count | 1
attribute_info | attributes | attributes_count - 1

</center>

当需要描述同一类型但数量不定的时候，都会通过一个前置容器计数器+若干个数据项的形式。

## 1.1 魔数与版本号

    使用魔数来确定这个文件是否为一个能被JVM接受的Class文件。
    值必须为Oxcafebabe。
    
## 1.2 常量池

    Class文件之中的资源仓库
    
常量池中主要存放字面量和符号引用。
- 字面量即文本字符串、final常量等
- 符号引用包括类和接口的全限定名、字段的名称和描述符、方法的名称和描述符

Java代码在进行编译的时候，没有连接这一步骤，而是由JVM加载Class文件的时候进行动态连接。因此在Class文件中不会保存各个方法、字段的最终内存布局信息。在虚拟机运行的时候，需要从常量池获得对应的符号引用，再在类创建或者运行的时候解析、翻译到具体的内存地址中。

### <center>常量池的项目类型</center>
<center>

类型 | 标志 | 描述
:-: | :-: | :-:
CONSTANT_Utf8_info | 1 | UTF-8编码的字符串
CONSTANT_Integer_info | 3 | 整型字面量
CONSTANT_Float_info | 4 | 浮点型字面量
CONSTANT_Long_info | 5 | 长整型字面量
CONSTANT_Double_info | 6 | 双精度浮点型字面量
CONSTANT_Class_info | 7 | 类或接口的符号引用
CONSTANT_String_info | 8 | 字符串类型字面量
CONSTANT_Field_info | 9 | 字段的符号引用
CONSTANT_Methodref_info | 10 | 方法的符号引用
CONSTANT_InterfaceMethodref_info | 11 | 接口中方法的符号引用
CONSTANT_NameAndType_info | 12 | 字段或方法的部分符号引用
CONSTANT_MethodHandle_info | 15 | 方法句柄
CONSTANT_MethodType_info | 16 | 方法类型
CONSTANT_Method_info | 18 | 一个动态方法调用点

</center>

### <center>常量池的14中常量项的结构总览</center>
![](http://oetw0yrii.bkt.clouddn.com/18-3-11/26496686.jpg)

## 1.3 访问标志

| 标志名称 | 标志值 | 含义
| :-: | :-: | :-: |
ACC_PUBLIC | 0x0001 | 是否public
ACC_FINAL | 0x0010 | 
ACC_SUPER | 0x0020
ACC_INTERFACE | 0x0200
ACC_ABSTRACT | 0x0400
ACC_SYNTHETIC | 0x1000
ANNOTATION | 0x2000
ENUM | 0x4000

## 1.4 类索引、父类索引与接口索引集合

- 类索引是一个u2类型的数据，用于确定该类的全限定名
- 父类索引是一个u2类型的数据，用于确定父类的全限定名
- 接口索引集合是一组u2类型的数据的集合，用来描述这个类实现了哪些接口，这些被实现的接口将按`implements`语句后的接口顺序从左到右排列早接口索引集合中

Class文件中由这三项数据来确定这个类的继承关系。

#### <center>类索引查找全限定名的过程</center>

![](http://oetw0yrii.bkt.clouddn.com/18-3-11/4410654.jpg)

## 1.5 字段表集合

    字段表，用于描述类或接口中声明的变量。
    
字段包括类级变量以及实例级变量，但不包括方法中声明的变量。

Java中描述一个字段需要的信息：
- 访问限制符
- 实例变量or类变量
- 可变性
- 并发可见性
- 是否可被序列化
- 数据类型
- 字段名称

各个修饰符都是都是bool值，而字段名、数据类型都是不固定的，只能引用常量池中的常量来描述。

#### <center>字段表结构</center>
```
field_info {
    u2 access_flags;
    u2 name_index;          // 对常量池的引用，代表字段的简单名称，即没有类型和参数修饰符的名称
    u2 descriptor_index;    // 对常量池的引用，代表字段的描述符，即字段的数据类型
    u2 attributes_count;
    attribute_info attribute[attributes_count];
}
```


#### <center>全限定名、简单名称和描述符</center>
名称 | 含义
:-: | :-:
全限定名 | 把类全限定名中的"/"换成"."，为了使多个连续的全限定名之间不产生混淆，在使用的时候最后一般会加入一个";"
简单名称 | 没有参数类型和参数修饰的字段和方法的名称
描述符 | 描述字段的数据类型、方法的参数列表和返回值

#### <center>描述符标识字符含义</center>

标识字符 | 含义
:-: | :-:
B | byte
C | char
D | double
F | float
F | float
I | int
J | long
S | short
Z | boolean
V | void
L | 对象，如Ljava/lang/Object;

字段表集合中不会列出从超类或者父类中集成而来的字段，但有可能列出原本代码中不存在的字段，譬如在内部类中为了保持对外部类的访问，会自动添加指向外部类实例的字段。

## 1.6 方法表集合
```
method_info {
    u2 access_flags;
    u2 name_index;          // 对常量池的引用，代表方法的简单名称，即没有类型和参数修饰符的名称
    u2 descriptor_index;    // 对常量池的引用，代表方法的描述符，即字段的数据类型
    u2 attributes_count;
    attribute_info attribute[attributes_count];
}
```

## 1.7 属性表集合

    在Class文件、字段表、方法表都可以携带自己的属性表集合，用于描述某些场景专有的信息。
    
属性表并不要求各个属性表有严格的顺序，只要不与已有的属性名重复，任何人实现的编译器都可以向属性表中写入自己定义的属性信息，JVM运行时会忽略掉不识别的属性。

#### <center>JVM规范预定义的属性</center>

属性名 | 使用位置 | 含义
:-: | :-: | :-: |
Code | 方法表 | Java代码编译成的字节码指令
ConstantValue | 字段表 | final关键字定义的常量值
Deprecated | 类、方法表、字段表 | 被声明为Deprecated
Exceptions | 方法表 | 方法抛出的异常
EnclosingMethod | 类文件 | 仅当一个类作为局部类或者匿名类时才拥有这个属性，这个属性用于标识这个类所在的外围方法
InnerClass | 类文件 | 内部类列表
LineNumberTable | Code属性 | Java源码的行号与字节码指令的对应关系
LocalVariableTable | Code属性 | 方法局部变量的描述
StackMapTable | Code属性 | JDK1.6中新增的属性，供新的类型检查验证器检车和处理目标方法的局部变量和操作数栈所需要的类型是否匹配
Signature | 类、方法表、字段表 | JDK1.5新增的属性，用于支持泛型情况下的方法签名，任何类、接口、初始化方法或成员的泛型签名如果包含了类型变量或参数化类型，则该属性会为它记录泛型签名信息。为了避免泛型信息被擦除后导致签名混乱，需要这个属性记录泛型中的相关信息。
SourceFile | 类文件 | 记录源文件名称
SourceDebugExtension | 类文件 | JDK1.6中新增的属性，用于存储额外的调试信息。
LocalVariableTypeTable | 类 | JDK1.5中新增的属性，使用特征签名代替描述符，是为了引入泛型语法之后能描述泛型参数化类型而添加


对于每个属性，它的名称都需要从常量池中引用一个`CONSTANT_Utf8_info`类型的常量来表示，而属性值的结构则完全是自定义的，只需要通过一个U4长度的属性去说明属性值所占用的位数即可

```
attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}
```

### 1.6.1 Code属性
Java程序方法体中的代码经过编译后，最终变为字节码指令存储在Code属性内。

类型 | 名称 | 数量
:-: | :-: | :-:
u2 | attribute_name_index | 1
u4 | attribute_length | 1
u2 | max_stack | 1
u2 | max_locals | 1
u4 | code_length | 1
u1 | code | code_length
u2 | exception_table_length | 1
exception_info | exception_table | exception_table_length
u2 | attributes_count | 1
attribute_info | attributes | attributes_count

# 2. 字节码指令简介

    JVM的指令由一个字节长度的操作码以及跟随其后的零至多个操作数构成。
    
Class文件放弃了编译后代码的操作数长度对齐，因此实际的操作方式为：

```java
do {
    // 自动计算PC寄存器的值+1
    // 根据PC寄存器指示的位置，从字节码中读取操作码
    if (字节码存在操作数) {
        // 从字节码中读取操作数
    }
    // 执行操作码所定义的操作
} while(字节码长度 > 0);
```

## 2.1 字节码与数据类型

    大多数指令都包含了其操作所对应的数据类型信息
    iload指令用于从局部变量表中加载int型的数据到操作数栈中
    
    

opcode | byte | short | int | long | float | double | char | reference | 含义
:-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-:
Tipush | bipush | sipush
Tconst |||iconst|lconst|fconst|dconst|| aconst |
Tload|||iload|lload|fload|dload||aload|
Tstore|||istore|lstore|fstore|dstore||astore|
Tinc|||iinc||||||
Taload|baload|saload|iaload|laload|faload|daload|caload|aaload|
Tastore|bastore|sastore|iastore|lastore|fastore|dastore|castore|aastore|
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
|||||||||
## 2.2 加载和存储指令

    加载和存储指令用于将数据在栈帧中的局部变量表和操作数栈之间来回传输
    
    
- [ ] 将一个局部变量加载到操作栈: `iload`、`iload_<n>`、`lload`、`lload_<n>`、`fload`、`fload_<n>`、`dload`、`dload_<n>`、`aload`、`aload_<n>`
- [ ] 将一个数值从操作数栈存储到局部变量表:`istore`、`istore_<n>`、`lstore`、`lstore_<n>`、`fstore`、`fstore_<n>`、`dstore`、`dstore_<n>`、`astore`、`astore_<n>`
- [ ] 将一个常量加载到操作数栈:`bipush`、`sipush`、`ldc`、`ldc_w`、`ldc2_w`、`aconst_null`、`iconst_m l`、`iconst_<i>`、`lconst<l>`、`fconst_<f>`、`dconst_<d>`

## 2.3 运算指令

    运算或算数指令用于到两个操作数栈上的值进行某种特定运算，并把结果重新存入到操作数栈顶。
    
具体的运算包括：
- 加法指令
- 减法指令
- 乘法指令
- 除法指令
- 求余指令
- 取反指令
- 位移指令
- 按位或指令
- 按为与指令
- 按位异或指令
- 局部变量自增指令
- 比较指令

## 2.4 类型转换指令

    将两种不同的数值类型进行相互转换。
    
## 2.5 对象创建于访问指令

    对象创建后，就可以通过对象访问指令获取对象实例或者数组实例中的字段或者数组元素
    
## 2.6 操作数栈管理指令

    类似于操作一个普通数据结构中的堆栈
    
## 2.7 控制转移指令

    控制转移指令可以让Java虚拟机有条件或无条件地从指定的位置指令的下一条指令继续执行程序。
    可以认为控制转移指令就是在有条件或无条件地修改PC寄存器的值。
    
## 2.8 方法调用和返回指令

- invokevirtual指令用于调用对象的实例方法，根据对象的实际类型进行分派。
- invokeinterface指令用于调用接口方法，会在运行时搜索一个实现了这个接口的方法的对象
- invokespecial指令用于调用一些需要特殊处理的实例方法，包括初始化方法、私有方法和父类方法
- invokestatic指令用于调用类方法
- invokedynamic指令用于在运行时动态解析出调用点限定符所引用的方法

## 2.9 异常处理指令

    在Java中显式抛出异常的操作都由athrow指令来实现
    而在JVM中，处理异常（catch）不是由字节码指令来实现的，而是采用异常表来完成的。
