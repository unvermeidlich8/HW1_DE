import RunResult.parameters.Parameters
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types._
import structures._


object TestUtils {
  val PATH_W4_SOH_CLIENT = "dataset/dataset/w4_soh_client.csv"
  val PATH_W4_SOH_ACNT_CONTRACT = "dataset/dataset/w4_soh_acnt_contract.csv"

  var tablesInited = 0

  var args: Array[String] = Array(
    "W4_SOH_CLIENT_TABLE=W4_SOH_CLIENT_TABLE",
    "W4_SOH_ACNT_CONTRACT_TABLE=W4_SOH_ACNT_CONTRACT_TABLE"
  )

  def createTable(tableName:String,schema:StructType,path:String,delimiter: String = ";")(implicit spark:SparkSession):Unit = {
    spark
      .read
      .option("header",value = true)
      .option("nullValue",null)
      .option("delimiter",delimiter)
      .schema(schema)
      .load(path)
      .createGlobalTempView(tableName)
    tablesInited += 1
  }

  def initTables(p:Parameters)(implicit spark:SparkSession):Unit = {
    createTable(tableName = s"$p.W4_SOH_ACNT_CONTRACT_TABLE",W4SohAcntContract.structType,PATH_W4_SOH_ACNT_CONTRACT)
    createTable(tableName = s"$p.W4_SOH_CLIENT_TABLE",W4SohClient.structType,PATH_W4_SOH_CLIENT)
    println(s"$tablesInited tables initialized")
  }
}
