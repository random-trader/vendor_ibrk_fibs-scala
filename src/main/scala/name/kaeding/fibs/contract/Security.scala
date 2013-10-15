package name.kaeding.fibs
package contract

import scalaz._, Scalaz._
import com.ib.client.{ Contract => IBContract }

sealed trait Security
case class Stock(
  symbol: String,
  securityId: Option[SecurityId] = none,
  currency: String = "USD") extends Security
  
object Stock {
  implicit object stockContract extends Contract[Stock] {
    def contract(s: Stock) = { 
      val ret = new IBContract()
      ret.m_symbol = s.symbol
      ret.m_secType = "STK"
      ret.m_exchange = "SMART"
      ret.m_currency = "USD"
      ret
    }
  }
}

case class StockOption(securityId: SecurityId) extends Security
case class Future(securityId: SecurityId) extends Security
case class Index(securityId: SecurityId) extends Security
case class FOP(securityId: SecurityId) extends Security
case class Cash(securityId: SecurityId) extends Security
case class BAG(securityId: SecurityId) extends Security