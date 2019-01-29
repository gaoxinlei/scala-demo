package com.example.scala.obj

/**
  * scala中的单例，借助伴生对象来实现。
  */
class Singleton private {

  private def init(): Unit = {
    println("--------init---------")
  }

  private def onCall(): Unit = {
    println("------call--------")
  }

  def add(a: Int, b: Int): Int = {
    a+b
  }

}

object Singleton {

  private val obj = new Singleton

  def init(): Unit = {
    obj.init()
  }

  def call(): Unit = {
    obj.onCall()
  }

}
