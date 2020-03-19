package controllers


import authentication.AuthenticationAction
import javax.inject.{Inject, Singleton}
import models.{EditForm, EventDetails, LoginDetails}
import play.api.mvc.{AbstractController, Action, AnyContent, Call, ControllerComponents, Request}

@Singleton
class EventController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction, val app: ApplicationUsingJsonReadersWriters) extends AbstractController(cc) with play.api.i18n.I18nSupport
{

  def event() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def eventSubmit(): Action[AnyContent] = authAction { implicit request :Request[AnyContent] =>
    EventDetails.Event.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.event(formWithErrors))
    }, { eventDetails =>
      Redirect( routes.ApplicationUsingJsonReadersWriters.create(eventDetails.event) )
    })
  }
  def viewAllEvents() = authAction { implicit request: Request[AnyContent] =>
    Redirect(routes.ApplicationUsingJsonReadersWriters.getAllEvents())
  }
  def deleteEvent(id :String) :Action[AnyContent] = authAction { implicit request :Request[AnyContent] =>
    Redirect( routes.ApplicationUsingJsonReadersWriters.deleteEvent(id))
  }
  def editEvent(id :String) :Action[AnyContent] = authAction { implicit request :Request[AnyContent] =>
    EditForm.editForm.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.editEvent(formWithErrors, id))
    }, editForm =>
      Redirect( routes.ApplicationUsingJsonReadersWriters.editEvent(id, editForm.newComment))
    )}

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
