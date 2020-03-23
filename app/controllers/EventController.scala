package controllers


import java.awt.Desktop.Action

import authentication.AuthenticationAction
import javax.inject.{Inject, Singleton}
import jdk.nashorn.internal.ir.RuntimeNode.Request
import models.{EditForm, eventDetails, LoginDetails}

@Singleton
class EventController @Inject()(cc: ControllerComponents, authAction: AuthenticationAction, val app: ApplicationUsingJsonReadersWriters) extends AbstractController(cc) with play.api.i18n.I18nSupport
{

  def event() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def eventSubmit(): Action[AnyContent] = authAction { implicit request :Request[AnyContent] =>
    EventDetails.EventController.bindFromRequest.fold({ formWithErrors =>
      BadRequest(views.html.event(formWithErrors))
    }, { EventDetails =>
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
    }, EditForm =>
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
