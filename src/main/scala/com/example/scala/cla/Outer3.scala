package com.example.scala.cla

import scala.beans.BeanProperty

/**
  * 利用投影保证多个外部类对象的内部类相同的办法
  */
class Outer3 {

  @BeanProperty
  var members = scala.collection.mutable.MutableList[Outer3#Member]()

  def join(name : String) ={
    val member = new Member(name)
    this.members+=member
    member
  }

  class Member(name : String){

    def desc = {
      this.name
    }
  }
}

