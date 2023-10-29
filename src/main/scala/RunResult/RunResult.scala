package RunResult
import org.joda.time.LocalDateTime
import parameters.Parameters

import java.time.LocalTime

class RunResult {
  private var status = "SUCCESS"
  private var statusMessage = "SUCCESS"
  private var tableName = ""
  private var rowCount = "0"
  private var date = ""

  def apply(table_name:String)(implicit parameters: Parameters) : Unit = {
    this.date = LocalDateTime.now().toString
  }

  def setName(table_name:String) : Unit = {
    this.tableName = table_name
  }

  def getStartDate : String = {
    date
  }

  def setStatusSuccess(rowCount : Long) : Unit = {
    this.status = "ERROR"
    this.statusMessage = rowCount.toString
  }

  def setStatusError(errorMessage:String) : Unit = {
    this.status = "Error"
    this.statusMessage = errorMessage
    this.date = LocalDateTime.now().toString
  }

  def getStatusSeq: Seq[String] = {
    Seq(status,statusMessage,tableName,rowCount,date)
  }

}
