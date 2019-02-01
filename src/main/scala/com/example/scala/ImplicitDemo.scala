package com.example.scala

import java.io.File


/**
  * 学习object，用object便于导入到当前作用域
  */
object LearnSkill {
  //隐式转换
  implicit def dragonCanFly(dragon: BigDragon) = new FlySkill
}

/**
  * 隐式转换demo
  * 分为隐式函数转换和隐式参数转换
  *
  * 隐式转换有两个时机：
  * 1)  当方法中的参数的类型与目标类型不一致时
  * 2)  当对象调用类中不存在的方法或成员时，编译器会自动将对象进行隐式转换
  *
  * 隐式的解析机制：
  * 即编译器是如何查找到缺失信息的，解析具有以下两种规则：
  * 1)  首先会在当前代码作用域下查找隐式实体（隐式方法、隐式类、隐式对象）。
  * 2)  如果第一条规则查找隐式实体失败，会继续在隐式参数的类型的作用域里查找。类型的作用域是指与该类型相关联的全部伴生模块，一个隐式实体的类型T它的查找范围如下：
  * a)  如果T被定义为T with A with B with C,那么A,B,C都是T的部分，在T的隐式解析过程中，它们的伴生对象都会被搜索。
  * b)  如果T是参数化类型，那么类型参数和与类型参数相关联的部分都算作T的部分，比如List[String]的隐式搜索会搜索List的伴生对象和String的伴生对象。
  * c)  如果T是一个单例类型p.T，即T是属于某个p对象内，那么这个p对象也会被搜索。
  * d)  如果T是个类型注入S#T，那么S和T都会被搜索。
  *
  * 隐式转换不能存在二义性且不能嵌套。
  *
  */
object ImplicitDemo {

  /**
    * 隐式类。
    * 它必须定义在一个object或class或伴生object或package对象中，因此不能写在ImplicitDemo之外，因为之外没有包对象
    * 它有且必须有一个参数的主构造器
    * 以下为性质
    * --  其所带的构造参数有且只能有一个
    * --  隐式类必须被定义在“类”或“伴生对象”或“包对象”里
    * --  隐式类不能是case class（case class在定义会自动生成伴生对象与2矛盾）
    * --  作用域内不能有与之相同名称的标示符
    *
    * @param name
    */
  implicit class StringIncrement(name: String) {
    def increment = name.map(s => (s + 1).toChar)
  }


  def main(args: Array[String]): Unit = {
    //定义一个在当前作用域范围内的隐式转换函数转换double到整型。
    implicit def double2Int(x: Double) = x.toInt

    //如果作用域内没有该函数，或该函数在本语句之后，将无作用。
    val a: Int = 3.3
    println(a)

    //用隐式函数扩展file类read方法demo
    implicit def file2Rich(file: File) = new CanReadFile(file)

    val file = new File("/gao/repo/scala-demo/pom.xml")
    println(file.read)
    //用隐式参数作为函数默认形参的demo
    implicit val pName = "big dragon"
    //    implicit val pName1 = "big dragon"//当有两个同类型隐式变量时，会报ambiguous错误
    implicit val pAge = 29

    def getName(implicit name: String) = name

    println(getName)

    //隐式视图，自动将参数进行转换
    //    1)  隐式转换为目标类型：把一种类型自动转换到另一种类型
    def printInt(a: Int): Unit = {
      println(a)
    }

    printInt(10.8) //前面定义了double2Int的隐函数。
    //2)  隐式转换调用类中本不存在的方法（可参考上面的file转为richFile,file中本无read方法，经过file2Rich转换
    //可直接调用file类中本不存在的read方法。
    //让大龙会飞的demo
    import com.example.scala.LearnSkill._
    val dragon = new BigDragon
    dragon.fly()
    //隐式类，隐含将abc变为bcd
    val str = "abc"
    println(str.increment)
  }

}

/**
  * 辅助富文件类，带有一个read方法。
  */
class CanReadFile(file: File) {

  def read = {
    file.getAbsolutePath.mkString
  }

}


/**
  * 大龙类，不会飞
  */
class BigDragon {

}

/**
  * 飞行技术类，会飞
  */
class FlySkill {
  def fly() = {
    println("会飞了")
  }
}




