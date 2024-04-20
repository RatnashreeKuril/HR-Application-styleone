package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import java.util.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class DesignationsView extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
DesignationDAO designationDAO=new DesignationDAO();
List<DesignationDTO> designations=designationDAO.getDesignations();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
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
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png;' style='float:left'></a><div style='margin-top:15px;margin-bottom:15px;font-size:25pt'>Thinking Machines</div>");
pw.println("</div>");
pw.println("<!-- Header ends here -->");
pw.println("<!-- Content-Section starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:70vh;border:1px solid white'>");
pw.println("<!-- Left panel starts here -->");
pw.println("<div style='margin:5px;height:65vh;border:1px solid black;float:left;padding:5px'>");
pw.println("<b>Designations</b><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- Left panel ends here -->");
pw.println("<!-- Right panel starts here -->");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:105px;height:65vh;border:1px solid black;padding:5px;overflow:scroll'>");
pw.println("<h2>Designations</h2>");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='4' style='text-align:right;padding:5px'><a href='/styleone/AddDesignation.html'>Add new designation</a></th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px;text-align:center'>S.No.</th>");
pw.println("<th style='width:200px;text-align:center'>Designation</th>");
pw.println("<th style='width:100px;text-align:center'>Edit</th>");
pw.println("<th style='width:100px;text-align:center'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
DesignationDTO designationDTO;
int sno=0;
for(int e=0;e<designations.size();e++)
{
sno++;
designationDTO=designations.get(e);
pw.println("<tr>");
pw.println("<td style='text-align:right'>"+sno+".</td>");
pw.println("<td>"+designationDTO.getTitle()+"</td>");
pw.println("<td><a href='/styleone/editDesignation?code="+designationDTO.getCode()+"'>Edit</a></td>");
pw.println("<td><a href='/styleone/confirmDeleteDesignation?code="+designationDTO.getCode()+"'>Delete</a></td>");
pw.println("</tr>");
}
pw.println("</tbody>");
pw.println("</table>");
pw.println("</div>");
pw.println("<!-- Right panel ends here -->");
pw.println("</div>");
pw.println("<!-- Content-Section ends here -->");
pw.println("<!-- Footer starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid white;text-align:center'>");
pw.println("&copy; Thinking Machines");
pw.println("</div>");
pw.println("<!-- Footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("</body>");
pw.println("</html>");



}catch(DAOException daoException)
{
System.out.println(daoException.getMessage()); //remove after testing and setup 500(internal error page)
}
catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing and setup 500(internal error page)
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}
}