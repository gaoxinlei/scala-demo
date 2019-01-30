package com.example.scala.extend

import scala.collection.mutable.ArrayBuffer

/**
  * 继承demo
  * 构造顺序：
  * 子类构造时先构造父类（参考java即可）
  */
object ExtendDemo {

  private def animalIsCat(animal: Animal) = {
      animal.isInstanceOf[Cat]
  }

  def main(args: Array[String]): Unit = {
    //类型检查和转换。
    val animal = new Cat("猫","大龙")
    println(animal.describe())
    val isCat = animalIsCat(animal)//发现一个scala奇葩的语法，左侧的变量名称右侧的函数名相同时不报错但运行报错。
    println(isCat)
    //匿名子类
    val anoymousAnimal = new Animal("大龙") {
      override protected val kind: String = "未发现物种"
      override protected val weight: Int = 60
      override protected var color: String = "变色"


      override def selfIntroduce(): String = "我的信息是:"+describe()
    }
  }

}

/**
  * 抽象类：不能实例化的类。
  * 类上标注abstract字段。
  * 方法上不标注abstract，只需要没有方法体即可，。
  * 字段只需要不提供初始值即可。
  * @param name
  */
abstract  class Animal(name : String){

  protected val kind : String//抽象字段，子类必须初始化,它不能私有。protect修饰的只能子类使用，本包非子类也不能使用
  protected val weight : Int
  protected var color : String

  private var arr = new ArrayBuffer[String](weight)//错误示例，这种依赖子类初始化的属性代码会造成tricky

  def describe() : String = {
    "类名:"+this.getClass.getName+",种类:"+this.kind+",昵称:"+this.name
  }

  /**
    * 抽象方法，不写方法体即可
    * @return
    */
  def selfIntroduce() : String
}

/**
  * 子类
  * 构造器字段要保持顺序，名称可重改。但子类构造器和父类构造器在extends前后中形参名要保持一致。
  * @param kind
  * @param name
  */
class Cat(val kind : String,val name : String) extends  Animal(name){

  //val只能重写val
  override val weight = 80//可不加override
  //var只能重写抽象的var
//  val color = "黄色"//用val会提示 需要将cat类置为abstract,因为animal中的color还未定义。但已经不会报编译错误，奇葩
  var color = "黄色"//可以加一个override
  /**
    * 重写方法
    * def只能重写def
    * @return
    */
  override def describe(): String = super.describe()

  /**
    * 实现父类的抽象方法。
    * 可以不加override，但加上可读性更好。
    *
    * @return
    */
  override def selfIntroduce(): String = {
    "hello : 我的信息："+describe()
  }
}
