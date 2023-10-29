package structures

import org.apache.spark.sql.types._

object W4SohAcntContract extends Enumeration {
    val id,client_id,odseffective_to_dt,odsis_active_flg,amnd_state,
    contract_number,comment_text,acnt_contract__oid,merchant_id,
    add_info_03,is_ready,hdp_processed_dttm = Value

    val structType : StructType = StructType(
      Seq(
        StructField(id.toString,StringType),
        StructField(client_id.toString,StringType),
        StructField(odseffective_to_dt.toString,StringType),
        StructField(odsis_active_flg.toString,StringType),
        StructField(amnd_state.toString,StringType),
        StructField(contract_number.toString,StringType),
        StructField(comment_text.toString,StringType),
        StructField(acnt_contract__oid.toString,StringType),
        StructField(merchant_id.toString,StringType),
        StructField(add_info_03.toString,StringType),
        StructField(is_ready.toString,StringType),
        StructField(hdp_processed_dttm.toString,StringType)
      )

    )
}
