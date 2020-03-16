package controllers

import javax.inject._
import play.api.mvc._
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class HomeController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction) extends AbstractController(cc) {

  def index = Action[AnyContent] = authAction {
    Ok(views.html.index("Your first new application is ready "))
  }
}
