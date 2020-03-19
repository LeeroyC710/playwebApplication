package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.play.json._
import collection._
import models.{Feed, EventDetails, Event}
import models.JsonFormats._
import play.api.libs.json.OWrites
import play.api.libs.json.{JsValue, Json}
import reactivemongo.api.Cursor

import play.modules.reactivemongo.{
  MongoController, ReactiveMongoComponents, ReactiveMongoApi
}

class AppUsingMongo @Inject()(
                               components: ControllerComponents,
                               val reactiveMongoApi: ReactiveMongoApi
                             ) extends AbstractController(components)
  with MongoController with ReactiveMongoComponents {

  implicit def ec: ExecutionContext = components.executionContext

  def collection: Future[JSONCollection] = database.map(_.collection[JSONCollection]("persons"))

  def Event(event: String, value: Any): Nothing = ???

  def create: Action[AnyContent] = Action.async {
    val event = Event("Corona Takeover", List(Feed("Slashdot news", "http://slashdot.org/slashdot.rdf")))
    val futureResult = collection.flatMap(_.insert.one(event))
    futureResult.map(_ => Ok("User inserted"))
  }

  def createFromJson: Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Event].map { event =>
      collection.flatMap(_.insert.one(event)).map { _ => Ok("event added")
      }
    }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

  def findByName(event: String): Action[AnyContent] = Action.async {
    val cursor: Future[Cursor[Event]] = collection.map {
      _.find(Json.obj("Corona Takeover" -> event)).
        sort(Json.obj("created" -> -1)).
        cursor[Event]()
    }

    val futureUsersList: Future[List[Event]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[Event]]()
        )
      )

    futureUsersList.map { persons =>
      Ok(persons.toString)
    }
  }



}