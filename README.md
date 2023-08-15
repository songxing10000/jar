
1|2|3|4
------------ | ------------- | ------------| ------------
![1](./imgs/Screenshot_20230605-205615.png) | ![2](./imgs/Screenshot_20230605-205623.png) |![3](./imgs/Screenshot_20230605-205650.png) |![4](./imgs/Screenshot_20230605-205655.png)
 
<details>
<summary>1.安装jar游戏模拟器</summary>

`tools`文件夹下的`J2ME_Loader-1.7.9-open-release.apk`

</details>


<details>
<summary>2.模拟器安装火焰-圣火徽章.jar</summary>

`jar`文件夹下的`火焰-圣火徽章.jar`

</details>

<details>
<summary>3.修改jar文件的工具Recaf</summary>

下载： `Recaf`：https://github.com/Col-E/Recaf


启动： 

```sh
java -jar /Users/dfpo/Downloads/recaf-2.21.13-J8-jar-with-dependencies.jar
```

 点菜单里的，`文件`、`加载`，选中`火焰-圣火徽章.jar`

</details>
 
终于把纹章1、2、3-1、3-2、3-3修改完成了，
大体上都是在设置我方角色名后，直接修改人物属性，去除3的倍数的回合才能使用地震，添加武器`地震`和耐久度不减的`星之珠`或`幸运之戒`的
![](./imgs/Snipaste_2023-08-15_15-54-31.png)
纹章1有个转职的说法，所以转职后得再次设置人物属性
![](./imgs/Snipaste_2023-08-15_17-52-05.png)

注意纹章3终章，只能把雅木设置最强属性70左右，因为后面有关卡，会复制我方人物属性。



其他可修改的地方

---
每关开始时，需要加载地图（如`m0`）文件，然后需要把我方敌方人物的数据(如`p0`)放到地图上去
![Alt text](./imgs/Snipaste_2023-07-14_10-43-35.png)



---
初始人物：雅木、希尔克、鲁卡、格雷的初始值需要在P0中修改，如下，修改了人物的初始血

![Alt text](./imgs/Snipaste_2023-07-15_11-01-56.png)
十六进制的`29`就是十进制的`41`，所以鲁卡的血为`41`如下图
![Alt text](./imgs/Snipaste_2023-07-14_22-18-06.png)


---
转职角色的初始值属性，商店道具价格
![](./imgs/Snip20230604_5.png)
![](./imgs/Snip20230630_3.png)

---
敌方人物的初始值属性 

![](./imgs/Snipaste_2023-07-10_22-49-10.png)


---
角色获得的经验
![](./imgs/Snip20230604_6.png)

---
根据获得的经验判断角色升级
![](./imgs/Snip20230604_7.png)


---
升级后，需要增加的属性增加多少值
![](./imgs/Snip20230604_8.png)

---
进商店更快些
![](./imgs/Snip20230604_9.png)

---
点购买金币，加多少钱
![](./imgs/Snip20230628_1.png)

---
武器耐久度

![](./imgs/Snipaste_2023-07-10_22-14-40.png)

---
访问房子
系统默认的
![](./imgs/Snipaste_2023-07-12_14-36-59.png)
我修改后的
![](./imgs/Snipaste_2023-07-12_16-11-55.png)

获取lang.dat文件的内容后，我们可以更简单地获取武器或物品的编号
![](./imgs/Snipaste_2023-08-08_15-14-23.png)




也可以根据需要修改，如放置两个`地震`，两个`星之珠`(武器耐久度不减)，直接双`地震`秒杀通关

--- 
商店整装装备个数限制
![](./imgs/Snipaste_2023-07-10_22-41-33.png)

---
给予或者访问房子获取物品个数限制
![Alt text](./imgs/Snipaste_2023-07-10_15-50-36.png)

---
往往地图上加入敌军
![Alt text](./imgs/Snipaste_2023-07-10_11-23-49.png)

---
![Alt text](./imgs/Snipaste_2023-07-11_16-51-54.png)

---
游戏通关结束时，显示名字等级战力
![](./imgs/Snip20230630_2.png)



---
读取`p0`文件，这样修改字节码打印又麻烦还容易出错打印int时。直接写个java把这里的混淆的代码更方便些，就像`ReadFileExample`文件中的`readPfile()`
![](./imgs/Snipaste_2023-07-14_15-11-52.png)
![](./imgs/Snipaste_2023-07-14_14-20-14.png)

---
如何向`p0`文件中多追加些敌人
![](./imgs/Snipaste_2023-07-15_22-16-01.png)

 加人处理下的ReadFileExample.java

 编译生成java字节码

 `javac ReadFileExample.java`

 运行java字节码

 `java ReadFileExample`

`ReadFileExample`主要实现了读取原始`p0`的内容。

以及向原始`jar`文件追加一个`P0`文件的功能。

为什么要读取原始`p0`内容:
想追加得在原始数据上追加新敌方，然后把新数据写入的`P0`文件内。

然后再打开新生成的`jar`文件，把原`p0`文件重命名为`p10`,再把

新加入的`P0`重命名为`p0`。注意`P0`里第一个是存放人物的个数的，追加了几个这里也得在原基础上加。

![](./imgs/Screenshot_20230715-231530.png)

```sh
// kt代码生成 jar
/Applications/Android\ Studio.app/Contents/plugins/Kotlin/kotlinc/bin/kotlinc ReadFileExample.kt -include-runtime -d ReadFileExample.jar
// 运行jar
java -jar ReadFileExample.jar
```

---
关于存档文件

![](./imgs/Snip20230605_2.png)

![](./imgs/Snip20230605_1.png)
那么问题来了，为了验证存的是啥，就得写个打印数组的方法的代码，然后手机连上`Android Studio`,看打印结果

---
新建安卓项目，
把代码复制进去，让其不报错，
```java
private static void a(final String s, final int n, final byte[] array) throws Exception {
        final RecordStore openRecordStore;
        int i;
        if ((i = (openRecordStore = RecordStore.openRecordStore(s, (boolean)(1 != 0))).getNumRecords()) <= n) {
            while (i < n) {
                openRecordStore.addRecord((byte[])null, 0, 0);
                ++i;
            }
            openRecordStore.addRecord(array, 0, array.length);
        }
        else {
            openRecordStore.setRecord(n + 1, array, 0, array.length);
        }
        openRecordStore.closeRecordStore();
        // 这里我们要追加打印这个数组
        final StringBuffer x = new StringBuffer();
        for (int j = 0; j < array.length; ++j) {
            x.append("--"+array[j]);
        }
        System.out.println(x);
    }
```

然后再生成apk,找出dex

![](./imgs/Snip20230605_3.png)

再用`dex-tools-2.1`，用`./d2j-dex2jar.sh classes3.dex`命令转换化jar
![](./imgs/Snip20230605_4.png)



,再用`Recaf`查看打印代码的字节码，再拷贝到`火焰-圣火徽章.jar`所需的方法中的字节码中,新加入的字节码得修改下，最后的全代码如下
```java
// 原方法代码
private static void a(final String s, final int n, final byte[] array) throws Exception {
        final RecordStore openRecordStore;
        int i;
        if ((i = (openRecordStore = RecordStore.openRecordStore(s, (boolean)(1 != 0))).getNumRecords()) <= n) {
            while (i < n) {
                openRecordStore.addRecord((byte[])null, 0, 0);
                ++i;
            }
            openRecordStore.addRecord(array, 0, array.length);
        }
        else {
            openRecordStore.setRecord(n + 1, array, 0, array.length);
        }
        openRecordStore.closeRecordStore();
         // 这里我们要追加打印这个数组
        final StringBuffer x = new StringBuffer();
        for (int j = 0; j < array.length; ++j) {
            x.append("--" + array[j]);
        }
        System.out.println(x);
    }
// java字节码
DEFINE PRIVATE STATIC a(Ljava/lang/String; 0, I 1, [B 2)V
THROWS java/lang/Exception
A:
ALOAD 0
ICONST_1
INVOKESTATIC javax/microedition/rms/RecordStore.openRecordStore(Ljava/lang/String;Z)Ljavax/microedition/rms/RecordStore;
DUP
ASTORE 3
INVOKEVIRTUAL javax/microedition/rms/RecordStore.getNumRecords()I
DUP
ISTORE 4
ILOAD 1
IF_ICMPGT D
B:
ILOAD 4
ILOAD 1
IF_ICMPGE C
ALOAD 3
ACONST_NULL
ICONST_0
ICONST_0
INVOKEVIRTUAL javax/microedition/rms/RecordStore.addRecord([BII)I
POP
IINC 4 1
GOTO B
C:
ALOAD 3
ALOAD 2
ICONST_0
ALOAD 2
ARRAYLENGTH
INVOKEVIRTUAL javax/microedition/rms/RecordStore.addRecord([BII)I
POP
GOTO E
D:
ALOAD 3
ILOAD 1
ICONST_1
IADD
ALOAD 2
ICONST_0
ALOAD 2
ARRAYLENGTH
INVOKEVIRTUAL javax/microedition/rms/RecordStore.setRecord(I[BII)V
E:
ALOAD 3
INVOKEVIRTUAL javax/microedition/rms/RecordStore.closeRecordStore()V
// 这里是我们加入的打印数组的字节码
NEW java/lang/StringBuffer
DUP
INVOKESPECIAL java/lang/StringBuffer.<init>()V
ASTORE 0
ICONST_0
ISTORE 3
F:
ILOAD 3
ALOAD 2
ARRAYLENGTH
IF_ICMPGE G
ALOAD 0
NEW java/lang/StringBuilder
DUP
INVOKESPECIAL java/lang/StringBuilder.<init>()V
LDC "--"
INVOKEVIRTUAL java/lang/StringBuilder.append(Ljava/lang/String;)Ljava/lang/StringBuilder;
ALOAD 2
ILOAD 3
BALOAD
INVOKEVIRTUAL java/lang/StringBuilder.append(I)Ljava/lang/StringBuilder;
INVOKEVIRTUAL java/lang/StringBuilder.toString()Ljava/lang/String;
INVOKEVIRTUAL java/lang/StringBuffer.append(Ljava/lang/String;)Ljava/lang/StringBuffer;
POP
IINC 3 1
GOTO F
G:
GETSTATIC java/lang/System.out Ljava/io/PrintStream;
ALOAD 0
INVOKEVIRTUAL java/io/PrintStream.println(Ljava/lang/Object;)V
RETURN
H:
I:

```

---
连上`Android Studio`,看打印结果。
操作下角色，点存储进度，就会有打印了

![](./imgs/Snip20230605_6.png)
后面就研究这个数组，就实现了修改存档了

---
终于看懂了这啥参数调用
![](./imgs/Snip20230604_11.png)



