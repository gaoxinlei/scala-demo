package com.example.scala.obj

/**
  * 使用apply代替构造器的示例
  * 它一般写在伴生对象中，并要在类上私有化构造器。
  */
object ApplyDemo {

  //先定义一个男人类和男人伴生对象。
  class Man private (name : String,age : Int){

    println("---init-----"+describe())

    def describe () : String = {
      "name:"+this.name+",age:"+this.age
    }
  }

  object Man{
    private var man : Man = null

    def apply(name: String, age: Int): Man = {
      if(null==man){
        man = new Man(name, age)
      }

      man
    }
  }

  def main(args: Array[String]): Unit = {
    val man = Man("gao",30)
    println(man.describe())
  }

}
