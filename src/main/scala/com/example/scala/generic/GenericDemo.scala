package com.example.scala.generic

/**
  * 泛型demo
  */
object GenericDemo {

  def main(args: Array[String]): Unit = {
    //泛型基本案例和视图边界demo
    val student = new Student[String]("大龙")
    val result = student.compare("山炮", "二逼")
    println(result)
    //泛型上下文界定demo
    val pair = new Pair(4, 5)
    println(pair.smaller)
    //泛型利用manifest取类型demo
    pair.getType
    //利用TypeTag获取类型
    println(findType(pair))
    println(findType(2))
    println(findType("aaa"))

  }

  /**
    * 泛型类。
    * 视图边界已被废弃，最好使用隐式参数转换。
    * T<:M 代表泛型参数T是M的子类。T<%M代表T可隐式转换为M。
    *
    * @param t
    * @tparam T
    */
  class Student[T <% Comparable[T]](t: T) {
    def compare(a: T, b: T): Boolean = {
      a.compareTo(b) > 0
    }
  }

  /**
    * 泛型的上下文界定。
    * 需要配合隐式转换使用。
    *
    * @param first
    * @param second
    * @tparam T
    */
  class Pair[T: Ordering](val first: T, val second: T) {

    def smaller(implicit ord: Ordering[T]): T = {
      println(ord)
      if (ord.compare(first, second) < 0) first else second
    }

    /**
      * 利用Manifest获取泛型类型
      *
      * @param typeParam
      */
    def getType(implicit typeParam: Manifest[T]): Unit = {
      /**
        * <:<代表是子类，已过期，提示用TypeTag代替。
        * =:=代表同类
        * 注意函数中使用的manifest不是首字母大写的类型，而是一个predef的函数。
        */
      if (typeParam <:< manifest[String]) {
        println("参数为字符串类型")
      }
      if (typeParam <:< manifest[Int]) {
        println("参数为整型")
      }
    }

  }

  //导包
  import scala.reflect.runtime.universe._

  //利用typetag来查看泛型类型
  def test2[A: TypeTag](x: List[A]) = typeOf[A] match {
    case t if t =:= typeOf[String] => "String List"
    case t if t =:= typeOf[Int] => "Int List"
  }

  def findType[T : TypeTag] (t : T) = typeOf[T] match {
      //因为类型不能直接用==所以不能case typeOf[String] => "String"
    case a if a =:= typeOf[String] => "String"
    case a if a =:= typeOf[Int] => "Int"
    case a if a =:= typeOf[Pair[Int]] => "Pair int"
  }

//  类型约束，提供了限定类型的另一种方式，一共有3中关系声明：
  //T =:= U意思为：T类型是否等于U类型
  //T <:< U意思为：T类型是否为U或U的子类型
  //T <%<U意思为：T类型是否被隐式（视图）转换为U

}

/*
关于型变的注释：
Mutable常常意味着Nonvariant，但是Noncovariant与Mutable分别表示两个不同的范畴。
即：可变的，一般意味着“不可型变”，但是“不可协变”和可变的，分别表示两个不同范畴。
型变(Variance)拥有三种基本形态：协变(Covariant), 逆变(Contravariant), 不变(Nonconviant)，可以形式化地描述为：
一般地，假设类型C[T]持有类型参数T；给定两个类型A和B，如果满足A <: B，则C[A]与 C[B]之间存在三种关系：
如果C[A] <: C[B]，那么C是协变的(Covariant);
如果C[A] :> C[B]，那么C是逆变的(Contravariant);
否则，C是不变的(Nonvariant)。
Scala的类型参数使用+标识“协变”，-标识“逆变”，而不带任何标识的表示“不变”(Nonvariable)：
trait C[+A]   // C is covariant
trait C[-A]   // C is contravariant
trait C[A]    // C is nonvariant
如何判断一个类型是否有型变能力：
一般地，“不可变的”(Immutable)类型意味着“型变”(Variant)，而“可变的”(Mutable)意味着“不变”(Nonvariant)。
其中，对于不可变的(Immutable)类型C[T]
如果它是一个生产者，其类型参数应该是协变的，即C[+T]；
如果它是一个消费者，其类型参数应该是逆变的，即C[-T]。



 */
