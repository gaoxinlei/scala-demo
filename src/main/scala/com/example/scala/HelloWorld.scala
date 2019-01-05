package com.example.scala

object HelloWorld {

  def main(args: Array[String]): Unit = {
    println("Hello Scala!"(4))//等同于"Hello Scala".apply(4)

    val arr = new Array[Int](3)
    arr(1)=2//等同于arr.update(1,2)
    arr.update(2,3)
    println(arr.mkString(","))

    val map = Map("a"->"alice","b"->"tom")//构建map测试optional
    println(map.get("a"))
    println(map.get("c"))
  }
}
