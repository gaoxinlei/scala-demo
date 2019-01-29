package com.example.scala.cla

/**
  * dog od
  * 主构造器中不带val或var修饰的参数被任务方法使用将自动升级为字段，类似于private[this] val 的效果
  */
class Dog(private val name :String) {

  println(name)
  private var legs = 4
  def shout(content: String) {
    println(content)
  }
  def currentLegs = legs


}
