package com.example.scala.cla


//类与对象demo
object ClassDemo {

  def main(args: Array[String]): Unit = {
    val dog = new Dog("Zhaolin")

    dog shout("来了来了")
    println(dog currentLegs)

    println(dog)

    //主构造器
    val book = new Book("蓝猫淘气三千问",300.8)
    println("------------")
    //调用person类中定义的方法
    val selfIntroduce = new Person("gao").selfIntroduce()
    println(selfIntroduce)
  }
}
