package com.example.scala.obj

/**
  * scala单例模式demo
  */
object SingletonDemo {

  def main(args: Array[String]): Unit = {
    val obj = Singleton//直接获取对象。
    obj.call()
    obj.init()
    //尝试直接new
//    val o = new Singleton()//运行时报错constructor Singleton in class cannot be accessed
//    println(o.add(2,3)) //scala默认构造器被我私有了，为什么编译不报错，奇葩。


  }

}
