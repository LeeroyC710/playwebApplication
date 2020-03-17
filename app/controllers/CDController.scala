//package controllers
//
//import actions.LoggingAction
//import javax.inject._
//import models.{CD, LoginDetails}
//import play.api.mvc.{Action, _}
//
//@Singleton
//class CDController @Inject()(cc: ControllerComponents, loggingAction: LoggingAction) extends AbstractController(cc) with play.api.i18n.I18nSupport {
//
//  def showCDForm() = Action { implicit request: Request[AnyContent] =>
//    Ok(views.html.submitCD(CD.createCDForm))
//  }
//
//  def submitCD = Action { implicit request: Request[AnyContent] =>
//    CD.createCDForm.bindFromRequest.fold({ formWithErrors =>
//      BadRequest(views.html.submitCD(formWithErrors))
//    }, { cd =>
//      Ok(cd.toString())
//    })
//  }
//  def loginSubmit: Action[AnyContent] = Action.async { implicit request =>
//    var loginDetails = LoginDetails(Constants.emptyString.toString, Constants.emptyString.toString)
//
//    LoginDetails.loginForm.bindFromRequest.fold({ formWithErrors =>
//      BadRequest(views.html.login(formWithErrors))
//    }, { details =>
//      println(details.username + " " + details.password)
//      loginDetails = details
//    })
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
