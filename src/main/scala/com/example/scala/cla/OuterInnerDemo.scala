package com.example.scala.cla

/**
  * scala中内部类和外部类的demo
  * 在scala中不同于java，一个内部类属于一个外部类的实例，不属于所有外部类的实例共享。
  */
object OuterInnerDemo {

  def main(args: Array[String]): Unit = {
    //先建一个outer1和inner1
    val o1 = new Outer
    val i1 = new o1.Inner("inner 1 ")
    val o2 = new Outer
    val i2 = new o2.Inner("inner 1 ")
    println(o1.getMembers)
    println(o2.getMembers)
    println(o1.getMembers.length)
    val newMembers = o1.getMembers++o1.getMembers//可以
    println(newMembers.length)
    val newMembers2 = o1.getMembers++o2.getMembers//可以
    println(newMembers2.length)

    val m1 = o1.join("li")
    val m2 = o2.join("l2")

    val members1 = o1.getMembers
    val members2 = o2.getMembers
    members1+=m1//可以
    //members1+=m2//不可以，m2和m1不属于一个outer

  }
}
