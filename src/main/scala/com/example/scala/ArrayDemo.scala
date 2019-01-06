package com.example.scala

import scala.collection.mutable.ArrayBuffer

/**
  * 定长与变长数组。
  */
object ArrayDemo {

  def main(args: Array[String]): Unit = {
    //定长数组两种方式。
    var arr1 = Array(1,3,2,3,3)
    println(arr1.mkString(","))
    val arr2 = new Array[Int](5)
    arr2(1)=3
    arr2(0)=2
    println(arr2.mkString(","))
    //变长数组
    val arr3 = new ArrayBuffer[Int]()
    arr3.append(3)
    arr3.append(4)
    println(arr3.mkString(","))
    //定长转变长
    arr1.toBuffer.append(3)//原arr1不会改变类型，需要使用一个新的可变数组接入append后的数组。
    println(arr1.mkString(","))
    //二维数组。
    val arr4 = Array.ofDim[Int](2,4)
    arr4(1)(1)=2
    println(arr4.mkString(","))
    //数组遍历
    for(i<-arr1){
      println(i)
    }
  }
}
