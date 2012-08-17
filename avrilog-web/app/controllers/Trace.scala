package controllers

import play.api._
import play.api.mvc._
import play.api.data.Forms._
import play.api.templates._
import models._
import views._
import play.api.mvc.Results._
import jp.t2v.lab.play20.auth._
import play.api.data._
import model.Account
import security._
import security.Permission._

object Trace extends Controller with Auth with AuthConfigImpl {

}
