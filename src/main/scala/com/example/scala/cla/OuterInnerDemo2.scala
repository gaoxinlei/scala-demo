package com.example.scala.cla

/**
  * 内部类demo2，使用伴生对象的方式，使所有不同外部类对象共享
  */
object OuterInnerDemo2 {

  def main(args: Array[String]): Unit = {
    val o1 = new Outer2
    val o2 = new Outer2
    val i1 = o1.join("i1")
    val i2 = o2.join("i2")
    val mems = o1.getMembers+=i2//用伴生对象的方式，member属于一个类。
    println(mems.length)//2
  }

}
