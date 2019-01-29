package com.example.scala.obj

/**
  * 枚举
  */
object TrafficLightColor extends Enumeration {

  //使用了id的情况下，id不可重复。
  val RED = Value(0,"STOP")
  val YELLOW = Value(1,"WAIT")
  val GREEN = Value(2,"GO")

}
