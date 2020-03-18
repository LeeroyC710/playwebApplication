package models

import anorm._
import anorm.SqlParser._
import play.api.db._
import play.api.Play.current

case class Event(id: Long, label: String)

object Event {

  val event = {
    get[Long]("id") ~
      get[String]("label") map {
      case id~label => Event(id, label)
    }
  }

  /**
   * Use Play DB.withConnection helper to create and automatically release a JDBC
   * connection. Then use Anorm SQL method to create the query. The 'as' method allows
   * parsing the ResultSet using the 'task *' parser -- it will parse as many rows as
   * possible and then return a 'List[Task]' since the 'task' parser returns a 'Task'.
   */
  def all(): List[Event] = DB.withConnection { implicit c =>
    SQL("select * from task").as(event *)
  }

  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("insert into task (label) values ({label})").on(
        'label -> label
      ).executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("delete from task where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  }

}
