package com.example.scala.obj

/**
  * 伴生类demo
  */
object AccompanyDemo {

  def main(args: Array[String]): Unit = {
    val bean = Accompany
    val obj = new Accompany
    println(obj.getCountry())//中国
    bean.changeCountry("美国")
    println(obj.getCountry())//中国。
    //本类是一个消除话语歧义的案例，即：
    //所谓伴生类和伴生对象可以互相访问对方私有属性，是指用对方的实例去访问。
    //如在伴生类中，直接用Accompany.country="美国"，或在伴生对象中new Accompany.country="美国",不用借助setter

    println(bean.getCountry())//美国
    obj.changeAccompanyCountry("中国")
    println(bean.getCountry())//中国，因为伴生对象类似静态，它是"单例"的，这个描述或许不恰当。

  }
}
