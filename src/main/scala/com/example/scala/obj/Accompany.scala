package com.example.scala.obj

import scala.beans.BeanProperty

/**
  * 伴生对象
  * 伴生对象和伴生类之间可以互相访问对方私有属性。
  * 伴生对象中的方法类似于静态方法。
  * 伴生类中的方法类似于实例方法
  * 伴生对象中定义的内部类自然相当于静态内部类。
  */
class Accompany {

  private var country : String  = "中国"
  @BeanProperty
  var name : String = ""

  def getCountry() : String = {
    country
  }

  def changeAccompanyCountry(countryName : String) : Unit = {
    Accompany.country = countryName//不需要用Accompany.changeCountry(countryName)
  }


}

object Accompany {

  private var country : String  = ""

  def changeCountry(countryName : String) : Unit = {
    this.country = countryName
  }

  def getCountry() : String = {
    this.country
  }
}


