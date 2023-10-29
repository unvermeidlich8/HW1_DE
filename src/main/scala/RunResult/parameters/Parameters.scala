package RunResult.parameters

import java.util.logging.Logger

class Parameters(args:Array[String])(implicit log: Logger) {
  private def pair(arg: String) : (String,String) = {
     val keyValue = arg.split("=", -1)
    (keyValue(0),keyValue(1))
  }

  private def getValue(key:String) : String = {
    this.argsMap.getOrElse(key,throw Exception)
  }

  private val argsMap: Map[String,String] = args.map(pair).toMap

  val W4_SOH_CLIENT_TABLE : String = getValue("W4_SOH_CLIENT_TABLE")
  val W4_SOH_ACNT_CONTRACT_TABLE : String = getValue("W4_SOH_ACNT_CONTRACT_TABLE")
}
