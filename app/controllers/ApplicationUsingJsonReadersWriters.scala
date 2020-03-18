package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import reactivemongo.play.json.collection.JSONCollection
import scala.concurrent.{ExecutionContext, Future}
import reactivemongo.play.json._
import collection._
import models.{Feed, EventDetails, Event}
import models.JsonFormats._
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

  def create: Action[AnyContent] = Action.async {
    val event = event("Corona Takeover", "22 April 2020", "Newbury Park", List(Feed("Slashdot news", "http://slashdot.org/slashdot.rdf")))
    val futureResult = collection.flatMap(_.insert.one(song))
    futureResult.map(_ => Ok("User inserted"))
  }

  def createFromJson: Action[JsValue] = Action.async(parse.json) { request =>
    request.body.validate[Event].map { event =>
      collection.flatMap(_.insert.one(event)).map { _ => Ok("event added")
      }
    }.getOrElse(Future.successful(BadRequest("invalid json")))
  }

  def findByName(event: String): Action[AnyContent] = Action.async {
    val cursor: Future[Cursor[Song]] = collection.map {
      _.find(Json.obj("Corona Takeover" -> event)).
        sort(Json.obj("created" -> -1)).
        cursor[Song]()
    }

    val futureUsersList: Future[List[Song]] =
      cursor.flatMap(
        _.collect[List](
          -1,
          Cursor.FailOnError[List[Song]]()
        )
      )

    futureUsersList.map { persons =>
      Ok(persons.toString)
    }
  }



}