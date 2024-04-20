package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class AddDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
String title="";
PrintWriter pw=null;
try
{
pw=response.getWriter();
response.setContentType("text/html");
title=request.getParameter("title");
DesignationDTO designationDTO=new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAO designationDAO=new DesignationDAO();
designationDAO.addDesignation(designationDTO);
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- Header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html><img src='/styleone/images/logo.png;' style='float:left'></a><div style='margin-top:15px;margin-bottom:15px;font-size:25pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<!-- Header ends here -->");
pw.println("<!-- Content-Section starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:70vh;border:1px solid white'>");
pw.println("<!-- Left panel starts here -->");
pw.println("<div style='margin:5px;height:65vh;border:1px solid black;float:left;padding:5px'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a><br><br>");
pw.println("</div>");
pw.println("<!-- Left panel ends here -->");
pw.println("<!-- Right panel starts here -->");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:105px;height:65vh;border:1px solid black;padding:5px'>");
pw.println("<h3>Notification</h3>");
pw.println("Designation Added<br>");
pw.println("Add more designations ?<br>");
pw.println("<table>");
pw.println("<td>");
pw.println("<tr>");
pw.println("<form action='/styleone/AddDesignation.html'>");
pw.println("<button type='submit'>Yes</button>");
pw.println("</form>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<form action='/styleone/designationsView'>");
pw.println("<button type='submit'>No</button>");
pw.println("</form>");
pw.println("</tr>");
pw.println("</td>");
pw.println("</table>");
pw.println("</div>");
pw.println("<!-- Right panel ends here -->");
pw.println("</div>");
pw.println("<!-- Content-Section ends here -->");
pw.println("<!-- Footer starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid white;text-align:center'>");
pw.println("&copy; Thinking Machines 2020");
pw.println("</div>");
pw.println("<!-- Footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("</body>");
pw.println("</html>");




}catch(DAOException daoException)
{

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var title=frm.title.value.trim();");
pw.println("var titleErrorSection=document.getElementById('titleErrorSection');");
pw.println("titleErrorSection.innerHTML='';");
pw.println("if(title.length==0)");
pw.println("{");
pw.println("titleErrorSection.innerHTML='Required';");
pw.println("frm.title.focus();");
pw.println("return false;");
pw.println("}");
pw.println("return true;");
pw.println("}");
pw.println("function cancelAddition()");
pw.println("{");
pw.println("document.getElementById('cancelAdditionForm').submit();");
pw.println("}");

pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- Header starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html><img src='/styleone/images/logo.png;' style='float:left'></a><div style='margin-top:15px;margin-bottom:15px;font-size:25pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<!-- Header ends here -->");
pw.println("<!-- Content-Section starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:70vh;border:1px solid white'>");
pw.println("<!-- Left panel starts here -->");
pw.println("<div style='margin:5px;height:65vh;border:1px solid black;float:left;padding:5px'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a><br><br>");
pw.println("</div>");
pw.println("<!-- Left panel ends here -->");
pw.println("<!-- Right panel starts here -->");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:105px;height:65vh;border:1px solid black;padding:5px'>");
pw.println("<h2>Designation (Add Module)</h2>");
pw.println("<div style='color:red'>"+daoException.getMessage()+"</div>");
pw.println("<form action='/styleone/addDesignation' onsubmit='return validateForm(this)'>");
pw.println("Designation");
pw.println("<input type='text' id='title' name='title' maxLength='35' size='36' value='"+title+"'>");
pw.println("<span id='titleErrorSection style='color:red'></span><br>");
pw.println("<button type='submit'>Add</button>");
pw.println("<button type='Button' onclick='cancelAddition()'>Cancel</button>");
pw.println("</form>");
pw.println("</div>");
pw.println("<!-- Right panel ends here -->");
pw.println("</div>");
pw.println("<!-- Content-Section ends here -->");
pw.println("<!-- Footer starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid white;text-align:center'>");
pw.println("&copy; Thinking Machines 2020");
pw.println("</div>");
pw.println("<!-- Footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("<form id='cancelAdditionForm' action='/styleone/designationsView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");


}
catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}
}