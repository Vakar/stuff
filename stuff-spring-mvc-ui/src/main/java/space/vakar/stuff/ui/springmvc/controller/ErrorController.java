package space.vakar.stuff.ui.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

  private static final String ERROR_PAGE = "errorPage";
  private static final String ERROR_ATTRIBUTE_NAME = "errorMsg";
  private static final String HTTP_ERROR_400_MESSAGE = "Http Error Code: 400. Bad Request";
  private static final String HTTP_ERROR_401_MESSAGE = "Http Error Code: 401. Unauthorized";
  private static final String HTTP_ERROR_404_MESSAGE = "Http Error Code: 404. Resource not found";
  private static final String HTTP_ERROR_500_MESSAGE =
      "Http Error Code: 500. Internal Server Error";
  private static final String HTTP_ERROR_UNKNOWN_MESSAGE = "Oops, something went wrong!!!";

  @GetMapping(value = "errors")
  public ModelAndView renderErrorPage(HttpServletRequest httpRequest) {
    String errorMsg;
    int httpErrorCode = getErrorCode(httpRequest);
    switch (httpErrorCode) {
      case 400:
        errorMsg = HTTP_ERROR_400_MESSAGE;
        break;
      case 401:
        errorMsg = HTTP_ERROR_401_MESSAGE;
        break;
      case 404:
        errorMsg = HTTP_ERROR_404_MESSAGE;
        break;
      case 500:
        errorMsg = HTTP_ERROR_500_MESSAGE;
        break;
      default:
        errorMsg = HTTP_ERROR_UNKNOWN_MESSAGE;
    }
    ModelAndView errorPage = new ModelAndView(ERROR_PAGE);
    errorPage.addObject(ERROR_ATTRIBUTE_NAME, errorMsg);
    return errorPage;
  }

  private int getErrorCode(HttpServletRequest httpRequest) {
    return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
  }
}
