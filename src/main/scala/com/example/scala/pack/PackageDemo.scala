package com.example.scala.pack

/**
  * 包demo
  */

package child{
  //写在子包child中的类或代码
  //在scala中，包不严格依赖于代码路径，同一文件下可以有多个包。
  //而且包可以包含类，对象和一些特质trait，但由于java虚拟机的限制，在包中无法定义函数和属性
  //要在包中定义函数和属性等，需要借且"包对象"来实现。
}

/**
  * 包对象，可以辅助在包中定义函数和属性。
  */
package object subpack{
  val packageName = "subpack"
}

/**
  * 子包和其中的类
  */
package subpack {

  import scala.collection.mutable

  /**
    * 测试包级别可见性。
    */
  object Visible{
    private[subpack] val home = "伊春"
    private[this] val building = "金水"

    private[subpack] def getBuilding() : String ={
      this.building
    }
  }

  object PackageDemo {

    def main(args: Array[String]): Unit = {
      val pn = packageName
      println("所在最后一级包名:"+packageName)
      //测试能否见到同包下其他对象的属性。
      println("visible类中的包共享私有属性:"+Visible.home)
      //println("visible类中的this私有属性:"+Visible.building)//编译错误，不可见。
      println("visible类中定义的包共享方法:"+Visible.getBuilding())
      //scala中可以在任何位置使用import
      import scala.collection.mutable.MutableList
      val a = MutableList(1,2,3)
      a+=4
      println(a.length)

      //import可以被重命名或隐藏，以便应对java和scala同时存在的包，如hashmap，大括号为选取器
      import java.util.{HashMap=>JavaHashMap}
      val map = new JavaHashMap[String,Object]()
      import scala.collection.mutable.{HashMap=>ScalaHashMap}
      val scalaMap = new ScalaHashMap[String,Object]()
      //使用选取器隐藏某个包名。
      import java.util.{List=>_}
      val list = List(1,2,3)
      //实际上每个scala程序都是隐式地以import java.lang._  import scala._  import Predef._ 开头

    }
  }

}
