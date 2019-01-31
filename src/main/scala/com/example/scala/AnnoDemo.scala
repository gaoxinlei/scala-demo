package com.example.scala

import com.sun.tools.doclint.Entity

import scala.beans.BeanProperty

/**
  * 注解demo
  * scala可以为类，方法，字段局部变量，参数，表达式，类型参数以及各种类型定义添加注解
  *
  */
object AnnoDemo {

  def main(args: Array[String]): Unit = {
    println("hello") : @unchecked//表达式注解，用：分隔
  }
}

/**
  * 类注解
  */
//@Entity class Student {
//
//  //方法注解
//  //  @Test def play() {}
//
//  //属性上的注解
//  @BeanProperty var username = ""
//
//  //参数上的注解
//  def doSomething(@NotNull message: String) {}
//
//  //@BeanProperty @Id var username = _
//}
//
////构造器注解。
////class Teacher @Inject() (name : String){}
