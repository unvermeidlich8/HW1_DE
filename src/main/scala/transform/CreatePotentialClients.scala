package transform

import RunResult.parameters.Parameters
import org.apache.spark.sql.{DataFrame, SparkSession}
import extract.Loader
import org.apache.log4j.Logger
import org.apache.spark.sql.functions.{count,lit,when}


object CreatePotentialClients {
  def createPotentialClients()(implicit spark: SparkSession, logger: Logger, parameters: Parameters): Unit = {
    import spark.implicits._
    val client = Loader.readW4SohClient()
    val acntContract = Loader.readW4SohAcntContract()

    val actualClient = client.
      join(acntContract,
        Seq("id"),
        "inner")
      .filter(
        'reg_number.rlike("^\\d{10}$") || 'reg_number.rlike("\\d{12}$"))
      .distinct()
        val sumMdm = actualClient
          .select(
            'id,
            'reg_number
          )
          .groupBy('reg_number.as("inn"))
          .agg(count('id).alias("sum_client_id"))
          .join(
            mdmEtalonClient
              .groupBy('tin.alias("inn"))
              .agg(count('mdmcode).as("sum_mdmcode")),
                Seq("inn"), "inner")
              .distinct()

        val potentialClient = actualClient
          .join(
            sumMdm,
            'inn === 'reg_number,
            "inner")
          .drop('col)
          .join(
            mdmEthalonClient,
            'tin === 'reg_number,
            "inner")
          .withColumn("mdm",when('sum_client_id === 1 && 'sum_mdmcode === 1,'mdmcode).otherwise(lit(null)))
          .select('reg_number)
  }
}
