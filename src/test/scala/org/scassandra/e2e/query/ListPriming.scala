package org.scassandra.e2e.query

import java.net.InetAddress
import java.util
import java.util.Date

import com.datastax.driver.core.DataType
import org.scassandra.AbstractIntegrationTest
import org.scassandra.cqlmessages.types._
import org.scassandra.priming.Success
import org.scassandra.priming.query.When

class ListPriming extends AbstractIntegrationTest {

  test("Test a list of varchars") {
    val list = List("one", "two", "three")
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlVarchar))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.varchar()))

    val expectedList = util.Arrays.asList("one","two","three")
    singleRow.getList("field", Class.forName("java.lang.String")) should equal(expectedList)
  }

  test("Test a list of ascii") {
    val list = List("one", "two", "three")
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlAscii))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.ascii()))

    val expectedList = util.Arrays.asList("one","two","three")
    singleRow.getList("field", Class.forName("java.lang.String")) should equal(expectedList)
  }

  test("Test a list of text") {
    val list = List("one", "two", "three")
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlText))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.text()))

    val expectedList = util.Arrays.asList("one","two","three")
    singleRow.getList("field", Class.forName("java.lang.String")) should equal(expectedList)
  }

  test("Test a list of int") {
    val list = List(1, 2, 3)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlInt))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.cint()))

    val expectedList = util.Arrays.asList(1,2,3)
    singleRow.getList("field", Class.forName("java.lang.Integer")) should equal(expectedList)
  }

  test("Test a list of bigint (java long)") {
    val list = List(1l, 2l, 3l)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlBigint))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.bigint()))

    val expectedList = util.Arrays.asList(1l,2l,3l)
    singleRow.getList("field", Class.forName("java.lang.Long")) should equal(expectedList)
  }

  test("Test a list of boolean") {
    val list = List(true, false, true)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlBoolean))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.cboolean()))

    val expectedList = util.Arrays.asList(true, false, true)
    singleRow.getList("field", Class.forName("java.lang.Boolean")) should equal(expectedList)
  }

  test("Test a list of counter") {
    val list = List(1l, 2l, 3l)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlCounter))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.counter()))

    val expectedList = util.Arrays.asList(1l, 2l, 3l)
    singleRow.getList("field", Class.forName("java.lang.Long")) should equal(expectedList)
  }

  test("Test a list of decimal") {
    val list = List(BigDecimal("1.2"), BigDecimal("2.3"), BigDecimal("3.4"))
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlDecimal))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.decimal()))

    val expectedList = util.Arrays.asList(new java.math.BigDecimal("1.2"), new java.math.BigDecimal("2.3"), new java.math.BigDecimal("3.4"))
    singleRow.getList("field", Class.forName("java.math.BigDecimal")) should equal(expectedList)
  }

  test("Test a list of double") {
    val list = List(1.0, 2.0, 3.0)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlDouble))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.cdouble()))

    val expectedList = util.Arrays.asList(1.0, 2.0, 3.0)
    singleRow.getList("field", Class.forName("java.lang.Double")) should equal(expectedList)
  }

  test("Test a list of float") {
    val list = List(1.0f, 2.0f, 3.0f)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlFloat))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.cfloat()))

    val expectedList = util.Arrays.asList(1.0f, 2.0f, 3.0f)
    singleRow.getList("field", Class.forName("java.lang.Float")) should equal(expectedList)
  }

  test("Test a list of inet") {
    val list = List(InetAddress.getLocalHost)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlInet))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.inet()))

    val expectedList = util.Arrays.asList(InetAddress.getLocalHost)
    singleRow.getList("field", Class.forName("java.net.InetAddress")) should equal(expectedList)
  }

  test("Test a list of date") {
    val date = new Date
    val list = List(date.getTime)
    val whenQuery = "Test prime with cql list"
    val rows: List[Map[String, Any]] = List(Map("field" -> list))
    val columnTypes  = Map("field" -> CqlList(CqlTimestamp))
    prime(When(query = Some(whenQuery)), rows, Success, columnTypes)

    val result = session.execute(whenQuery)

    val singleRow = result.one()
    singleRow.getColumnDefinitions.getType("field") should equal(DataType.list(DataType.timestamp()))

    val expectedList = util.Arrays.asList(date)
    singleRow.getList("field", Class.forName("java.util.Date")) should equal(expectedList)
  }

  //todo uuid
  //todo timeuuid
  //todo varint
  //todo blob
}
