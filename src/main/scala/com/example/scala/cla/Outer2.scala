package com.example.scala.cla

import com.example.scala.cla.Outer2.Inner

import scala.beans.BeanProperty
import scala.collection.mutable

/**
  * 利用伴生对象解决多个外部对象创建不同类型内部类对象的情况
  */
class Outer2 {


  @BeanProperty
  var members : scala.collection.mutable.MutableList[Inner] = new mutable.MutableList[Inner]()


  /**
    * 加入并返回该成员
    * @param desc
    * @return
    */
  def join(desc :String) ={
    val member=new Inner(desc)
    members+=member
    member
  }

}

object  Outer2{

  @BeanProperty
  var members : scala.collection.mutable.MutableList[Inner] = new mutable.MutableList[Inner]()

  /**
    * 内部类
    * @param desc
    */
  class Inner(desc : String){
    members+=this
  }
}