package com.example.scala.cla

import scala.beans.BeanProperty

class Book(bookName: String,price: Double) {
  //在scala中，直接写在类中执行的逻辑操作会自动封装进主构造器。
  //相应的，非var val修饰的主构造器参数若被使用，将成为私有字段，即private[this]，只在本类范围内，相应的也有private[package]等
  print("书名:"+bookName+",价格:"+price)

  @BeanProperty//带上此注解的，将自动生成getter setter方法，使用getXxx  setXxx类似java的操作，但原生的getter setter仍在
  var name: String = _

}
