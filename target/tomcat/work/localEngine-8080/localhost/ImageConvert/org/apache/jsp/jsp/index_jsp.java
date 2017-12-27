package org.apache.jsp.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/easyui_themes/color.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/easyui_themes/icon.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/easyui_themes/mobile.css\"/>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"../css/easyui_themes/default/easyui.css\"/>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/jquery.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/load_image/load-image.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/load_image/load-image-meta.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/load_image/load-image-exif.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/load_image/load-image-exif-map.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/load_image/load-image-orientation.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/pica/pica.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/megapix-image.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"../js/imageConvert.js\"></script>\r\n");
      out.write("<title></title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("    <h2>Image Convert Demo</h2>\r\n");
      out.write("    <p>==========================.</p>\r\n");
      out.write("    <div style=\"margin:20px 0;\"></div>\r\n");
      out.write("    <div class=\"easyui-layout\" style=\"width:100%;height:500px;\">\r\n");
      out.write("        <div data-options=\"region:'west',split:true\" title=\"上傳設定\" style=\"width:40%;height: 100%\">\r\n");
      out.write("        \t<form action=\"\">\r\n");
      out.write("        \t\t<table id=\"uploadSetting\"  style=\"width:100%\" border=\"2\">\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>上傳圖片</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input type='file'  class=\"upImg\">\r\n");
      out.write("\t\t\t\t\t\t    <div>\r\n");
      out.write("\t\t\t\t\t\t        <img class=\"preview\" style=\"max-width: 150px; max-height: 150px;\">\r\n");
      out.write("\t\t\t\t\t\t        <div class=\"size\"></div>\r\n");
      out.write("\t\t\t\t\t\t    </div>\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>壓縮品質(JS_pica)</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input id=\"pica_quality\" style=\"width:100px\">\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>縮放品質(JAVA_Scarl)</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<select id=\"scarl_quality\"  class=\"easyui-combobox\" style=\"width:150px\">\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"-1\">ALL</option>\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"0\">AUTOMATIC</option>\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"1\">BALANCED</option>\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"2\">QUALITY</option>\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"3\">SPEED</option>\r\n");
      out.write("\t\t\t\t\t\t\t    <option value=\"4\">ULTRA_QUALITY</option>\r\n");
      out.write("        \t\t\t\t\t</select>\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>壓縮品質(JAVA_jpeg)</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input id=\"jpeg_quality\" style=\"width:100px\">\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>高斯模糊</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input id=\"gs_smooth\" style=\"width:100px\">\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>高斯模糊</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input id=\"blur_radis\" style=\"width:100px\">\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>高斯模糊</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input id=\"sharp_radis\" style=\"width:100px\">\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td>目標尺寸</td>\r\n");
      out.write("        \t\t\t\t<td>\r\n");
      out.write("        \t\t\t\t\t<input type=\"radio\" id=\"dpi200\" name=\"targetDPI\" value=\"200\" checked> <label for=\"dpi200\" >200DPI</label>\r\n");
      out.write("        \t\t\t\t\t<input type=\"radio\" id=\"dpi300\" name=\"targetDPI\" value=\"300\"> <label for=\"dpi300\">300DPI</label>\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t\t<tr>\r\n");
      out.write("        \t\t\t\t<td colspan=\"2\">\r\n");
      out.write("        \t\t\t\t\t<input type=\"button\"  id=\"submit\" name=\"submit\"  value=\"送出\">\r\n");
      out.write("        \t\t\t\t</td>\r\n");
      out.write("        \t\t\t</tr>\r\n");
      out.write("        \t\t</table>\r\n");
      out.write("        \t</form>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div id=\"result\" class=\"result\"></div>\r\n");
      out.write("        <div data-options=\"region:'center',split:true\" title=\"訊息\" style=\"width:60%;height: 100%\">\r\n");
      out.write("        \t<textarea id=\"detail\" rows=\"21\" cols=\"90\"  style=\"resize:none;overflow-y: scroll;\" readonly=\"readonly\"></textarea>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</body>\r\n");
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
