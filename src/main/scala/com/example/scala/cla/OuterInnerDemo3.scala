package com.example.scala.cla

/**
  * 使用类投影保证多个外部类对象使用同一个内部类
  */
object OuterInnerDemo3 {

  def main(args: Array[String]): Unit = {

    val o1 = new Outer3
    val o2 = new Outer3
    val members = o1.getMembers
    println(members.length)//0
    val i1 = o1.join("i1")
    val i2 = o2.join("i2")
    println(members.length)//1
    members+=i2
    println(members.length)//2
    val desc = (for (m <- members) yield m.desc).mkString(", ")
    println(desc)//i1, i2
  }
}
