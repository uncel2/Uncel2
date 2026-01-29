<%-- 
    Document   : search.jsp
    Created on : 22-01-2026, 10:31:44
    Author     : tungi
--%>

<%@page import="model.UniversityDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="welcome.jsp" />

        <!-- <--%@include  file="welcome.jsp" %> -->

        <%-- <%
            if ((UserDTO) session.getAttribute("user") != null) {
                String keywords = request.getAttribute("keywords")+"";
                keywords = keywords.equals("null")?"":keywords;
                ArrayList<UniversityDTO> list = new ArrayList<UniversityDTO>();
                if(request.getAttribute("list")!=null){
                    list = (ArrayList<UniversityDTO>)request.getAttribute("list");
                }
                
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="search"/>
            Input name: <input type="text" name="keywords" value="<%=keywords%>" />
            <input type="submit" value="search"/>
        </form>
        <hr/>
        <table border="1">
            <thead>
                <th>Id</th>
                <th>Name</th>
                <th>Short Name</th>
                <th>City</th>
                <th>Region</th>
                <th>Type</th>
                <th>Founded Year</th>
                <th>Students</th>
                <th>Faculties</th>
                <th></th> 
            </thead>
            <%
                if(list.size()==0){
            %>
                No data matching the search criteria found!
            <%
                } else {
                    for (UniversityDTO u : list) {
            %>
                        <tr>
                            <td><%=u.getId()%></td>
                            <td><%=u.getName()%></td>
                            <td><%=u.getShortName()%></td>
                            <td><%=u.getCity()%></td>
                            <td><%=u.getRegion()%></td>
                            <td><%=u.getType()%></td>
                            <td><%=u.getFoundedYear()%></td> 
                            <td><%=u.getTotalStudents()%></td>
                            <td><%=u.getTotalFaculties()%></td>
                            <td>
                                <input type="submit" value="Update" style="display: <%=u.isIsDraft()?"block":"none"%>" />                                
                            </td>
                        </tr>  
            <%
                    }
                }
            %>
        </table>
        <%  } else {
                response.sendRedirect("login.jsp");
            }
%> --%>
        <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>

        <c:if test="${not empty user}">

            <form action="MainController" method="post">
                <input type="hidden" name="action" value="search"/>
                Input name:
                <input type="text" name="keywords" value="${keywords}" />
                <input type="submit" value="Search"/>
            </form>
            <hr/>

            <c:if test="${empty list}">
                No data matching the search criteria found!
            </c:if>

            <c:if test="${not empty list}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Short Name</th>
                            <th>City</th>
                            <th>Region</th>
                            <th>Type</th>
                            <th>Founded Year</th>
                            <th>Students</th>
                            <th>Faculties</th>
                            <th></th>
                        </tr>
                    </thead>

                    <c:forEach items="${list}" var="u">
                        <tr>
                            <td>${u.id}</td>
                            <td>${u.name}</td>
                            <td>${u.shortName}</td>
                            <td>${u.city}</td>
                            <td>${u.region}</td>
                            <td>${u.type}</td>
                            <td>${u.foundedYear}</td>
                            <td>${u.totalStudents}</td>
                            <td>${u.totalFaculties}</td>
                            <td>
                                <c:if test="${u.isDraft}">
                                    <input type="submit" value="Update"/>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </c:if>
    </body>
</html>