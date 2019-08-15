// app/controllers/Callback.scala

package controllers

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

import javax.inject.Inject
import play.api.cache._
import play.api.http.HeaderNames
import play.api.http.MimeTypes
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.Json.toJsFieldJsValueWrapper
import play.api.libs.ws._
import play.api.mvc.Action
import play.api.mvc.Controller
import helpers.Auth0Config

class Callback @Inject() (cache: CacheApi, ws: WSClient) extends Controller {

  def callback(codeOpt: Option[String] = None, stateOpt: Option[String] = None) = Action.async { request =>
    val sessionId = request.session.get("id").get
    if (stateOpt == cache.get(sessionId + "state")) {
      (for {
        code <- codeOpt
      } yield {
        getToken(code, sessionId).flatMap { case (idToken, accessToken) =>
          getUser(accessToken).map { user =>
            cache.set(request.session.get("id").get + "profile", user)
            Redirect(routes.User.index())
              .withSession(
                "idToken" -> idToken,
                "accessToken" -> accessToken
              )
          }

        }.recover {
          case ex: IllegalStateException => Unauthorized(ex.getMessage)
        }
      }).getOrElse(Future.successful(BadRequest("No parameters supplied")))
    } else {
      Future.successful(BadRequest("Invalid state parameter"))
    }
  }

  def getToken(code: String, sessionId: String): Future[(String, String)] = {

    val tokenResponse = ws.url(String.format("https://%s/oauth/token", "dev-2a6ya7vr.auth0.com")).
      withHeaders(HeaderNames.ACCEPT -> MimeTypes.JSON).
      post(
        Json.obj(
          "client_id" -> "fm9Y8AfeoKiN7OTHVJbdwQJD7JBvMpO3",
          "client_secret" -> "YOUR_CLIENT_SECRET",
          "redirect_uri" -> "http://localhost:3000/callback",
          "code" -> code,
          "grant_type"-> "authorization_code",
          "audience" -> "http://localhost"
        )
      )

    tokenResponse.flatMap { response =>
      (for {
        idToken <- (response.json \ "id_token").asOpt[String]
        accessToken <- (response.json \ "access_token").asOpt[String]
      } yield {
        cache.set(sessionId + "id_token", idToken)
        cache.set(sessionId + "access_token", accessToken)
        Future.successful((idToken, accessToken))
      }).getOrElse(Future.failed[(String, String)](new IllegalStateException("Tokens not sent")))
    }

  }

  def getUser(accessToken: String): Future[JsValue] = {
    val config = Auth0Config.get()
    val userResponse = ws.url(String.format("https://%s/userinfo", "dev-2a6ya7vr.auth0.com"))
      .withQueryString("access_token" -> accessToken)
      .get()

    userResponse.flatMap(response => Future.successful(response.json))
  }
}