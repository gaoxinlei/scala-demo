package com.example.scala.obj

/**
  * 枚举示例
  */
object EnumDemo {

  def main(args: Array[String]): Unit = {
    println(TrafficLightColor.GREEN)//GO
    println(TrafficLightColor.GREEN.id)//2
    println(TrafficLightColor.RED)
    println(TrafficLightColor.RED.id)
    println(TrafficLightColor.YELLOW)
    println(TrafficLightColor.YELLOW.id)
  }

}
