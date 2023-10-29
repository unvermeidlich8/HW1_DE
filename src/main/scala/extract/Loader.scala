package extract

import RunResult.parameters.Parameters
import org.apache.log4j.Logger
import org.apache.spark.sql.{DataFrame, SparkSession}

object Loader {
  def readW4SohClient()(implicit spark:SparkSession,logger : Logger,parameters :Parameters) : DataFrame = {
    logger.info(s"reading ${parameters.W4_SOH_CLIENT_TABLE}")
    import spark.implicits._
    spark.read.table(s"${parameters.W4_SOH_CLIENT_TABLE}")
      .select(
        'id,
        'odsis_active_flg,
        'amnd_state,
        'odseffective_to_dt
      )
      .filter(
        'odsis_active_flg === "1" && 'amnd_state === 'A'
      )
  }

  def readW4SohAcntContract()(implicit spark:SparkSession,logger : Logger,parameters :Parameters) : DataFrame = {
    logger.info(s"reading ${parameters.W4_SOH_ACNT_CONTRACT_TABLE}")
    import spark.implicits._
    spark.read.table(s"${parameters.W4_SOH_ACNT_CONTRACT_TABLE}")
      .select(
        'id,
        'client__id,
        'add_info_03,
        'contact_number,
        'comment_text,
        'merchant_id,
        'amnd_state,
        'is_ready,
        'odsis_active_flg,
        'odseffective_to_dt,
        'acnt_contract__oid
      )
      .filter(
        'odsis_active_flg === "1" && 'amnd_state === 'A'
          && 'is_ready =!= "C"
      )
  }
}
