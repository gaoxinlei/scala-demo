package com.example.scala.cla

import scala.beans.BeanProperty
import scala.collection.mutable

/**
  * 外部类
  */
class Outer {


  @BeanProperty
  var members : scala.collection.mutable.MutableList[Inner] = new mutable.MutableList[Inner]()
  /**
    * 内部类
    * @param desc
    */
  class Inner(desc : String){
    members+=this
  }

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
