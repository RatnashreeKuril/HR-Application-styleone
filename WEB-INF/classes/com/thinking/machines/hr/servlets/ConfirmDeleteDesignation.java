package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.util.*;
public class ConfirmDeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
int code=0;
try
{
code=Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException numberFormatException)
{
// send back view page
sendBackView(response);

return;
}
DesignationDAO designationDAO=new DesignationDAO();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
DesignationDTO designationDTO=null;
try
{
designationDTO=designationDAO.getDesignationByCode(code);
}catch(DAOException daoException)
{
// send back view page
sendBackView(response);
return;
}


pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function cancelDeletion()");
pw.println("{");
pw.println("document.getElementById('cancelDeletionForm').submit();");
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
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- Left panel ends here -->");
pw.println("<!-- Right panel starts here -->");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:105px;height:65vh;border:1px solid black;padding:5px'>");
pw.println("<h2>Designation (Delete Module)</h2>");
pw.println("<form method='post' action='/styleone/deleteDesignation'>");

pw.println("<input type='hidden' id='code' name='code' value='"+code+"'>");
pw.println("Designation : ");
pw.println("<b>"+designationDTO.getTitle()+"</b><br><br>");
pw.println("Are you sure you want to delete this designation ?<br><br>");
pw.println("<button type='submit'>Yes</button>");
pw.println("<button type='Button' onclick='cancelDeletion()'>No</button>");
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
pw.println("<form id='cancelDeletionForm' action='/styleone/designationsView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");


}catch(Exception exception)
{
System.out.println(exception.getMessage()); // remove after testing
}
}
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}
private void sendBackView(HttpServletResponse response)
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
pw.println("&copy; Thinking Machines 2020");
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
}