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

<html>
  <head>
    <title>Content-List</title>
      <style>
          h1 {
              color: black;
          }
      </style>
  </head>
  <body>
<% Path path = Paths.get("C:\\Users\\StripeScott\\Desktop\\Tomcat\\src\\main\\java\\org\\example");
    List<Path> pathList = Files.walk(path).filter(x -> x.getFileName().endsWith(".java")).toList();
    for(Path javaPath : pathList) {
        String fileName = javaPath.getFileName().toString();
        String name = fileName.substring(0, fileName.indexOf("."));
        try {
            Class<?> cl = Class.forName("org.example." + name);
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().equals(ContentServlet.class)) { %>

                    <h1>Hello World!</h1>

<%              }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    };%>
  </body>
</html>
