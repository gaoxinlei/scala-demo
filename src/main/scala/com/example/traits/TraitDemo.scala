package com.example.traits

import java.time.LocalDateTime

/**
  * 特质 ：trait demo
  *
  * 类似java，scala也是一门面向对象的语言，同样不允许多重继承。
  * 特质 trait类似java的接口（准备说像java8以后的），可以继承多个trait，trait本身也可以继承class，
  * 且trait中可以有抽象属性，抽向方法，具体属性或具体方法等。
  * 类能过extends继承一个trait，可以用with继承多个trait
  */
object TraitDemo {

  def main(args: Array[String]): Unit = {

    //基本案例，特质的继承和实现
    val fruit = new Fruit(18)
    fruit.sell()
    fruit.eat()
    //带有具体实现的特质，及带有特质的对象的动态混入
    val fatMan = new Man("老高", 198) with DateTimeLogger
    fatMan.eat(fruit)
    //提示超过200斤不能吃
    //    2019-01-30T16:11:48.778 : 老高,吃了这个水果你就大于200斤了
    val thinMan = new Man("大龙", 140) with BasicLogger
    thinMan.eat(fruit) //提示吃完多少斤
    //吃了:18斤水果，体重变为:158斤

    //测试特质迭加。
    val timeFirstMan = new Man("老赵", 196) with DateTimeLogger with WarnLogger
    timeFirstMan.eat(fruit)
    //时间，warn，消息
    //2019-01-30T16:11:48.778 : +++warn::老赵,吃了这个水果你就大于200斤了
    val warnFirstMan = new Man("老刘", 193) with WarnLogger with DateTimeLogger
    warnFirstMan.eat(fruit) //warn在前，时间，消息
    //+++warn::2019-01-30T16:11:48.779 : 老刘,吃了这个水果你就大于200斤了s

    //自身类型demo
    val self = new Self
    self.logTypeName

  }
}


/**
  * 能吃的 trait
  */
trait Eatable {

  def eat()
}

/**
  * 可售卖的trait
  */
trait Marketable {

  def sell()
}

class Fruit(weight: Int) extends Eatable with Marketable {


  override def eat() = {
    println("吃了" + weight + "斤水果")
  }

  override def sell() = {
    println("卖了" + weight + "斤水果")

  }

  def getWeight(): Int = {
    this.weight
  }
}

/**
  * 特质不能有构造器参数，每个特质都有一个无参数的构造器。缺少构造器参数是特质与类之间唯一的技术差别
  * logger trait
  */
trait BaseLogger {
  val suffix: String //特质中的抽象字段

  //特质的构造器
  log("特质：BaseLogger开始构造")

  /**
    * 打印日志
    *
    * @param msg
    */
  def log(msg: String)

  //富接口特性，具备非抽象方法。
  def debug(msg: String): Unit = {
    log("DEBUG:" + msg)
  }

  def info(msg: String): Unit = {
    log("INFO:" + msg)
  }

  def warn(msg: String): Unit = {
    log("WARN:" + msg)
  }

  def error(msg: String): Unit = {
    log("ERROR:" + msg)
  }
}

/**
  * 修饰的logger
  */
trait DateTimeLogger extends BasicLogger {

  //构造器
  log("特质DateTimeLogger开始构造")

  override def log(msg: String) = {
    super.log(LocalDateTime.now() + " : " + msg)
  }
}

/**
  * trait可以有实际的方法
  */
trait BasicLogger extends BaseLogger {

  private val prefix = "来自BasicLogger:"
  //构造器
  log("特质:BasicLogger开始构造")
  //特质中的具体字段
  override val suffix: String = "=>来自BasicLogger"

  override def log(msg: String): Unit = {
    println(prefix + msg + suffix)
  }
}

trait WarnLogger extends BasicLogger {
  //构造器
  log("特质：WarnLogger开始构造")

  override def log(msg: String): Unit = {
    super.log("+++warn::" + msg)
  }
}

abstract class Person(weight: Int) extends BaseLogger {
  def eat(fruit: Fruit): Unit = {
    //    log("吃了:"+fruit.getWeight()+"斤水果，体重变为:"+(this.weight+fruit.getWeight())+"斤")
    warn("吃了:" + fruit.getWeight() + "斤水果，体重变为:" + (this.weight + fruit.getWeight()) + "斤")
  }

  def getWeight() = {
    this.weight
  }
}

abstract class Man(name: String, weight: Int) extends Person(weight) {

  //超过两百斤不吃。
  override def eat(fruit: Fruit) = {
    if (getWeight() + fruit.getWeight() > 200) {
      //      log(name+",吃了这个水果你就大于200斤了")
      error(name + ",吃了这个水果你就大于200斤了")
    } else {
      super.eat(fruit)
    }
  }
}

/**
  * 无构造参数man　类
  */
class NoArgsMan{

}
/**
  * 特质可以继承普通类，则所有混入该特质的类将等于继承了该超类
  * 因物质不带构造器，故不能继承带构造器的man
  */
trait SuperMan extends NoArgsMan {

}

/**
  * 继承特质
  * 因特质已经继承了Man类，此类只能再显式继承Man的子类而不能继承其他类，否则将造成多继承
  */
class PoliceMan extends SuperMan with BasicLogger {

}

/**
  * 特质的自身类型
  * 混入man类型。
  * 特质的自身类型主要是为了解决特质的循环依赖问题，
  * 同时可以确保特质在不扩展某个类的情况下，依然可以做到限制混入该特质的类的类型
  */
trait ManSelfType {
  this: Man =>
}

trait BasicSelfType {
  val typeName: String
  def logTypeName
}

/**
  * 扩展basic self type，得到typeName
  */
trait SubSelfType {
  this: BasicSelfType =>

  def logTypeName(): Unit = {
    println(typeName)
  }
}

/**
  * 自我类型。
  * 不加上with后面这一段，编译报错：非法继承，自我类型self不能转换为BasicSelfType,加上with并初始化
  * typeName，则可通过编译。
  */
class Self extends SubSelfType  with BasicSelfType {
  override val typeName: String = "未知类型"
}








