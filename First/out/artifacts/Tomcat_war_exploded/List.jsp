<%--
  Created by IntelliJ IDEA.
  User: StripeScott
  Date: 04.09.2022
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="org.example.ContentServlet" %>
<%@ page import="java.lang.annotation.Annotation" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="jakarta.servlet.annotation.WebServlet" %>

<% PrintWriter outln = response.getWriter(); %>
<html>
  <head>
    <title>Content-List</title>
      <style>
          h1 {
              position: absolute;
              left: 50%;
              top: 50%;
              font-size: xx-large;
          }
          div{
              width: 400px;
              height: 50px;
              border: 5px black groove;
              word-wrap: break-word;
              float: left;
          }
      </style>
  </head>
  <body>
<% Path path = Paths.get(request.getServletContext().getRealPath("WEB-INF/classes/org/example"));
    List<Path> pathList = Files.walk(path).filter(x -> x.toString().endsWith(".class")).toList();
    for(Path javaPath : pathList) {
        String fileName = javaPath.getFileName().toString();
        String name = fileName.substring(0, fileName.indexOf("."));
        try {
            Class<?> cl = Class.forName("org.example." + name);
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ContentServlet.class)) {
                    String contentName = cl.getAnnotation(ContentServlet.class).contentName();
                    String[] displayName = cl.getAnnotation(WebServlet.class).value();
                        outln.println("<div>");

                        outln.println(contentName + ":");
                        outln.println("<button onclick=\"window.location.href='" + request.getContextPath()
                                + displayName[0] + "'\">Go</button>");

                        outln.println("</div>");
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    };%>
  <h1>Здесь ничего нет</h1>
  </body>
</html>
