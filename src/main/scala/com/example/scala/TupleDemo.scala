package com.example.scala

/**
  * 元组样例
  */
object TupleDemo {
  def main(args: Array[String]): Unit = {
    val tup1 = (1,2,3,4,5)
    //元组用_i访问第i个元素，i从1开始。
    println(tup1._4)
    println(tup1)
    //遍历
    for(ele<-tup1.productIterator){
      println(ele)
    }
  }
}
