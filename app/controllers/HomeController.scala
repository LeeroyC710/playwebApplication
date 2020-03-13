package controllers

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index = Action {
    Ok(views.html.index("Your first new application is ready "))
  }
  def help() = Action {
    Ok("There is no help here...")
  }
}
