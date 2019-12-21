package space.vakar.stuff.ui.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

  private static final String HTTP_ERROR_400_TITLE = "Error Code: 400";
  private static final String HTTP_ERROR_400_MESSAGE = "Bad Request.";
  private static final String HTTP_ERROR_401_TITLE = "Error Code: 401";
  private static final String HTTP_ERROR_401_MESSAGE = "You are not authorized.";
  private static final String HTTP_ERROR_404_TITLE = "Error Code: 404";
  private static final String HTTP_ERROR_404_MESSAGE = "Page doesn't exists.";
  private static final String HTTP_ERROR_500_TITLE = "Error Code: 500";
  private static final String HTTP_ERROR_500_MESSAGE = "Internal Server Error.";
  private static final String HTTP_ERROR_UNKNOWN_TITLE = "Error Code: Unknown error";
  private static final String HTTP_ERROR_UNKNOWN_MESSAGE = "Oops, something went wrong.";

  @GetMapping(value = "errors")
  public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
    String errorTitle;
    String errorMsg;
    int httpErrorCode = getErrorCode(httpRequest);
    switch (httpErrorCode) {
      case 400:
        errorTitle = HTTP_ERROR_400_TITLE;
        errorMsg = HTTP_ERROR_400_MESSAGE;
        break;
      case 401:
        errorTitle = HTTP_ERROR_401_TITLE;
        errorMsg = HTTP_ERROR_401_MESSAGE;
        break;
      case 404:
        errorTitle = HTTP_ERROR_404_TITLE;
        errorMsg = HTTP_ERROR_404_MESSAGE;
        break;
      case 500:
        errorTitle = HTTP_ERROR_500_TITLE;
        errorMsg = HTTP_ERROR_500_MESSAGE;
        break;
      default:
        errorTitle = HTTP_ERROR_UNKNOWN_TITLE;
        errorMsg = HTTP_ERROR_UNKNOWN_MESSAGE;
    }
    ModelAndView page = new ModelAndView("info");
    page.addObject("title", errorTitle);
    page.addObject("message", errorMsg);
    return page;
  }

  private int getErrorCode(HttpServletRequest httpRequest) {
    return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
  }
}
