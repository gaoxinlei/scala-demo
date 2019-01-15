package com.example.scala

class Amount {

  case class Dollar(value:Double) extends  Amount
  case class Currency(value:Double,unit : String) extends Amount
  case object Nothing extends  Amount

  def test(): Unit = {
    for (amt <- Array(Dollar(1000.0), Currency(1000.0, "EUR"), Nothing)) {
      val result = amt match {
        case Dollar(v) => "$" + v
        case Currency(_, u) => u
        case Nothing => ""
      }
      println(amt + ": " + result)
    }

    //copy 带名参数
    val amt = Currency(1200,"yuan")
    val rmb = amt.copy(value=900)
    val rmb1 = amt.copy()
    println(rmb)
    println(rmb1)
  }

}

object Amount{
  def main(args: Array[String]): Unit = {
    new Amount().test()
  }
}

