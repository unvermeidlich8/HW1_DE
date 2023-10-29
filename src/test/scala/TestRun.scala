import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object TestRun extends App{
  implicit val logger : Logger = Logger.getLogger(getClass)

  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("netty").setLevel(Level.ERROR)

  implicit val Spark: SparkSession = SparkSession
    .builder()
    .appName("DE2")
    .master("local[1]")
    .enableHiveSupport()
    .getOrCreate()


  Spark.stop()
}
