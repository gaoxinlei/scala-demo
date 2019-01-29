package com.example.scala.cla

/**
  * 辅助构造器demo
  *
  */
class Person {

  private var name : String = ""
  def this(name : String){
    //这个this()不可省，必须调用其他辅助构造器或主构造器
    this()
    this.name = name
  }

  /**
    * 定义在类中的叫方法，定义在object中的叫函数，可以当作一个变量去赋值。
    * @return
    */
  def selfIntroduce() :String = {
    return "My name is :"+this.name
  }

}
