package controllers

import authentication.AuthenticationAction
import javax.inject.{Inject, Singleton}
import models.{EventDetails, LoginDetails}
import play.api.mvc.{AbstractController, Action,  AnyContent, Call, ControllerComponents, Request}

@Singleton
class EventController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction, val app: ApplicationUsingJsonReadersWriters) extends AbstractController(cc) with play.api.i18n.I18nSupport
{

  def event() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(EventDetails.postForm))
  }

  def eventSubmit = Action[AnyContent] ; authAction { implicit request: Request[AnyContent] =>
    EventDetails.eventForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.event(formWithErrors))
    }, { EventDetails =>
      Redirect( routes.ApplicationUsingJsonReadersWriters.create(postDetails.post))
    })
  }
  def viewAllEvents() = authAction { implicit request: Request[AnyContent] =>
    Redirect(routes.ApplicationUsingJsonReadersWriters.getAllEvents())
  };
}
    //    mongoServices.validUser(loginDetails).map (result => {
//      if (result) {
//        println("Valid user")
//        Redirect(routes.Application.index())
//          .withSession(request.session + (Constants.username.toString -> loginDetails.username))
//          .flashing(Constants.login.toString -> Constants.loginMessage.toString)
//      } else {
//        println("Invalid user")
//        Redirect(routes.LoginController.login())
//      }
//    })
//  }
//}
