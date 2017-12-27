package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class systemInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/jquery.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/jquery.easyui.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/client.min.js\"></script>\r\n");
      out.write("<title>SysInfo</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("\t$(function() {\r\n");
      out.write("\t\tvar client = new ClientJS();\r\n");
      out.write("\t\t\r\n");
      out.write("        document.write(\"getFingerprint\"+\" : \"+ client[\"getFingerprint\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getCustomFingerprint\"+\" : \"+ client[\"getCustomFingerprint\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getUserAgent\"+\" : \"+ client[\"getUserAgent\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getBrowser\"+\" : \"+ client[\"getBrowser\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getBrowserVersion\"+\" : \"+ client[\"getBrowserVersion\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isIE\"+\" : \"+ client[\"isIE\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isChrome\"+\" : \"+ client[\"isChrome\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isFirefox \"+\" : \"+ client[\"isFirefox\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isSafari\"+\" : \"+ client[\"isSafari\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileSafari\"+\" : \"+ client[\"isMobileSafari\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isOpera\"+\" : \"+ client[\"isOpera\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getOS\"+\" : \"+ client[\"getOS\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isWindows\"+\" : \"+ client[\"isWindows\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMac\"+\" : \"+ client[\"isMac\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isLinux\"+\" : \"+ client[\"isLinux\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isUbuntu\"+\" : \"+ client[\"isUbuntu\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isSolaris\"+\" : \"+ client[\"isSolaris\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getDevice\"+\" : \"+ client[\"getDevice\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getDeviceType\"+\" : \"+ client[\"getDeviceType\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getCPU\"+\" : \"+ client[\"getCPU\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobile\"+\" : \"+ client[\"isMobile\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileMajor\"+\" : \"+ client[\"isMobileMajor\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileAndroid\"+\" : \"+ client[\"isMobileAndroid\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileOpera\"+\" : \"+ client[\"isMobileOpera\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileWindows\"+\" : \"+ client[\"isMobileWindows\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileBlackBerry\"+\" : \"+ client[\"isMobileBlackBerry\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isMobileIOS\"+\" : \"+ client[\"isMobileIOS\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isIphone\"+\" : \"+ client[\"isIphone\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isIpad\"+\" : \"+ client[\"isIpad\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isIpod\"+\" : \"+ client[\"isIpod\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getCurrentResolution\"+\" : \"+ client[\"getCurrentResolution\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getAvailableResolution\"+\" : \"+ client[\"getAvailableResolution\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getDeviceXDPI\"+\" : \"+ client[\"getDeviceXDPI\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getDeviceYDPI\"+\" : \"+ client[\"getDeviceYDPI\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isJava\"+\" : \"+ client[\"isJava\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getJavaVersion\"+\" : \"+ client[\"getJavaVersion\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isFlash\"+\" : \"+ client[\"isFlash\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getFlashVersion\"+\" : \"+ client[\"getFlashVersion\"]()+'<br/>');\r\n");
      out.write("        document.write(\"isSilverlight\"+\" : \"+ client[\"isSilverlight\"]()+'<br/>');\r\n");
      out.write("        document.write(\"getSilverlightVersion\"+\" : \"+ client[\"getSilverlightVersion\"]()+'<br/>');\r\n");
      out.write("\t});\r\n");
      out.write("</script>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
