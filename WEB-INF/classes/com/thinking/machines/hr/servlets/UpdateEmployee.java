package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import java.util.*;
import java.io.*;
import java.math.*;
import java.text.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class UpdateEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
try
{
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
String employeeId=request.getParameter("employeeId");
String name=request.getParameter("name");
int designationCode=Integer.parseInt(request.getParameter("designationCode"));
java.util.Date dateOfBirth=simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender=request.getParameter("gender");
String isIndian=request.getParameter("isIndian");
if(isIndian==null) isIndian="N";
BigDecimal basicSalary=new BigDecimal(request.getParameter("basicSalary"));
String panNumber=request.getParameter("panNumber");
String aadharCardNumber=request.getParameter("aadharCardNumber");
EmployeeDAO employeeDAO=new EmployeeDAO();
DesignationDAO designationDAO=new DesignationDAO();
try
{

if(employeeDAO.employeeIdExists(employeeId)==false)
{
sendBackView(response);
return;
}


boolean designationCodeExists=designationDAO.designationCodeExists(designationCode);

boolean panNumberExists=false;
EmployeeDTO employeeDTO;
try
{
employeeDTO=employeeDAO.getByPANNumber(panNumber);
if(employeeDTO.getEmployeeId().equalsIgnoreCase(employeeId)==false)
{
panNumberExists=true;
}
}catch(DAOException daoException)
{
panNumberExists=false;
}

boolean aadharCardNumberExists=false;
try
{
employeeDTO=employeeDAO.getByAadharCardNumber(aadharCardNumber);
if(employeeDTO.getEmployeeId().equals(employeeId)==false)
{
aadharCardNumberExists=true;
}
}catch(DAOException daoException)
{
aadharCardNumberExists=false;
}



if(designationCodeExists==false || panNumberExists==true || aadharCardNumberExists==true)
{
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var valid=true;");
pw.println("var firstInvalidComponent=null;");
pw.println("var name=frm.name.value.trim();");
pw.println("var nameErrorSection=document.getElementById('nameErrorSection');");
pw.println("nameErrorSection.innerHTML='';");
pw.println("if(name.length==0)");
pw.println("{");
pw.println("nameErrorSection.innerHTML='Name required';");
pw.println("valid=false;");
pw.println("firstInvalidComponent=frm.name;");
pw.println("}");
pw.println("");
pw.println("var designationCode=frm.designationCode.value;");
pw.println("var designationCodeErrorSection=document.getElementById('designationCodeErrorSection');");
pw.println("designationCodeErrorSection.innerHTML='';");
pw.println("if(designationCode==-1)");
pw.println("{");
pw.println("designationCodeErrorSection.innerHTML='Select designation';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.designationCode;");
pw.println("}");
pw.println("");
pw.println("var dateOfBirth=frm.dateOfBirth.value;");
pw.println("var dateOfBirthErrorSection=document.getElementById('dateOfBirthErrorSection');");
pw.println("dateOfBirthErrorSection.innerHTML='';");
pw.println("if(dateOfBirth.length==0)");
pw.println("{");
pw.println("dateOfBirthErrorSection.innerHTML='Select date of birth';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.dateOfBirth;");
pw.println("}");
pw.println("");
pw.println("var genderErrorSection=document.getElementById('genderErrorSection');");
pw.println("genderErrorSection.innerHTML='';");
pw.println("if(frm.gender[0].checked==false && frm.gender[1].checked==false)");
pw.println("{");
pw.println("genderErrorSection.innerHTML='Select gender';");
pw.println("valid=false;");
pw.println("}");
pw.println("");
pw.println("var basicSalary=frm.basicSalary.value.trim();");
pw.println("var basicSalaryErrorSection=document.getElementById('basicSalaryErrorSection');");
pw.println("basicSalaryErrorSection.innerHTML='';");
pw.println("if(basicSalary.length==0)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Basic salary required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("var e=0;");
pw.println("var v='0123456789.'");
pw.println("var isBasicSalaryValid=true;");
pw.println("while(e<basicSalary.length)");
pw.println("{");
pw.println("if(v.indexOf(basicSalary.charAt(e))==-1)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid basic salary';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;");
pw.println("isBasicSalaryValid=false;");
pw.println("break;");
pw.println("}");
pw.println("e++;");
pw.println("}");
pw.println("if(isBasicSalaryValid)");
pw.println("{");
pw.println("var dot=basicSalary.indexOf('.');");
pw.println("if(dot!=-1)");
pw.println("{");
pw.println("var numberOfFractions=basicSalary.length-(dot+1);");
pw.println("if(numberOfFractions>2)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid basic salary';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.basicSalary;");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("}");
pw.println("");
pw.println("");
pw.println("var panNumber=frm.panNumber.value.trim();");
pw.println("var panNumberErrorSection=document.getElementById('panNumberErrorSection');");
pw.println("panNumberErrorSection.innerHTML='';");
pw.println("if(panNumber.length==0)");
pw.println("{");
pw.println("panNumberErrorSection.innerHTML='PAN number required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.panNumber;");
pw.println("}");
pw.println("");
pw.println("var aadharCardNumber=frm.aadharCardNumber.value.trim();");
pw.println("var aadharCardNumberErrorSection=document.getElementById('aadharCardNumberErrorSection');");
pw.println("aadharCardNumberErrorSection.innerHTML='';");
pw.println("if(aadharCardNumber.length==0)");
pw.println("{");
pw.println("aadharCardNumberErrorSection.innerHTML='Aadhar card number required';");
pw.println("valid=false;");
pw.println("if(firstInvalidComponent==null) firstInvalidComponent=frm.aadharCardNumber;");
pw.println("}");
pw.println("if(!valid) firstInvalidComponent.focus();");
pw.println("return valid;");
pw.println("}");
pw.println("function cancelUpdate()");
pw.println("{");
pw.println("document.getElementById('cancelUpdateForm').submit();");
pw.println("}");
pw.println("</script>");
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
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- Left panel ends here -->");
pw.println("<!-- Right panel starts here -->");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:105px;height:65vh;border:1px solid black;padding:5px'>");
pw.println("<h2>Employee (Edit Module)</h2>");
pw.println("<form method='post' action='/styleone/updateEmployee' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' name='employeeId' id='employeeId' value='"+employeeId+"'>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Name</td>");
pw.println("<td><input type='text' id='name' name='name' maxlength='50' size='51' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td><select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");
List<DesignationDTO> designations=designationDAO.getDesignations();
String title;
int code;
for(DesignationDTO designation:designations)
{
code=designation.getCode();
title=designation.getTitle();
if(code!=designationCode)
{
pw.println("<option value='"+code+"'>"+title+"</option>");
}
else
{
pw.println("<option selected value='"+designation.getCode()+"'>"+designation.getTitle()+"</option>");
}
}
pw.println("</select>");
if(designationCodeExists==false)
{
pw.println("<span id='designationCodeErrorSection' style='color:red'>Invalid designation</span></td>");
}
else
{
pw.println("<span id='designationCodeErrorSection' style='color:red'></span></td>");
}

pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth</td>");
pw.println("<td><input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>Gender</td>");
pw.println("<td>");
if(gender.equals("M"))
{
pw.println("<input checked type='radio' id='male' name='gender' value='M'> Male");
}
else
{
pw.println("<input type='radio' id='male' name='gender' value='M'> Male");
}
pw.println("&nbsp;&nbsp;&nbsp;&nbsp");

if(gender.equals("F"))
{
pw.println("<input checked type='radio' id='female' name='gender' value='F'> Female");
}
else
{
pw.println("<input type='radio' id='female' name='gender' value='F'> Female");
}
pw.println("<span id='genderErrorSection' style='color:red'>");
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>Indian</td>");
pw.println("<td>");

if(isIndian.equals("Y"))
{
pw.println("<input checked type='checkbox' id='isIndian' name='isIndian' value='Y'>");
}
else
{
pw.println("<input type='checkbox' id='isIndian' name='isIndian' value='Y'>");
}

pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic salary</td>");
pw.println("<td>");
pw.println("<input type='text' id='basicSalary' name='basicSalary' style='text-align:right' maxlength='12' size='13' value='"+basicSalary.toPlainString()+"'>");
pw.println("<span id='basicSalaryErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>PAN Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='"+panNumber+"'>");
if(panNumberExists)
{
pw.println("<span id='panNumberErrorSection' style='color:red'>PAN number exists</span>");
}
else
{
pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td>Aadhar card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");
if(aadharCardNumberExists)
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>Aadhar card number exists</span>");
}
else
{
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span>");
}
pw.println("</td>");
pw.println("</tr>");
pw.println("");
pw.println("<tr>");
pw.println("<td colspan='2'><button type='submit'>Update</button>");
pw.println("&nbsp;&nbsp;<button type='Button' onclick='cancelUpdate()'>Cancel</button></td>");
pw.println("</tr>");
pw.println("</table>");
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
pw.println("<form id='cancelUpdateForm' action='/styleone/employeesView'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
return; // update form resent because of problems
}
}catch(DAOException daoException)
{
System.out.println(daoException); // remove after testing
}
 

EmployeeDTO employee=new EmployeeDTO();
employee.setEmployeeId(employeeId);
employee.setName(name);
employee.setDesignationCode(designationCode);
employee.setDateOfBirth(dateOfBirth);
employee.setGender(gender);
employee.setIsIndian(isIndian.equals("Y"));
employee.setBasicSalary(basicSalary);
employee.setPANNumber(panNumber);
employee.setAadharCardNumber(aadharCardNumber);

try
{
employeeDAO.update(employee);

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
pw.println("Employee Updated<br>");
pw.println("<form action='/styleone/employeesView'>");
pw.println("<button type='submit'>Ok</button>");
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
pw.println("</body>");
pw.println("</html>");



}catch(DAOException daoException)
{
// recreate form with error message at top of form
// and send back the page
}
}catch(Exception exception)
{

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
EmployeeDAO employeeDAO=new EmployeeDAO();
List<EmployeeDTO> employees=employeeDAO.getAll();
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function Employee()");
pw.println("{");
pw.println("this.employeeId=\"\";");
pw.println("this.name=\"\";");
pw.println("this.designationCode=0;");
pw.println("this.designation=\"\";");
pw.println("this.dateOfBirth=\"\";");
pw.println("this.gender=\"\";");
pw.println("this.isIndian=true;");
pw.println("this.basicSalary=0;");
pw.println("this.panNumber=\"\";");
pw.println("this.aadharCardNumber=\"\";");
pw.println("}");
pw.println("var employees=[];");
pw.println("var selectedRow=null;");
pw.println("var i=0;");
SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTO employee:employees)
{
pw.println("var employee=new Employee();");
pw.println("employee.employeeId=\""+employee.getEmployeeId()+"\";");
pw.println("employee.name=\""+employee.getName()+"\";");
pw.println("employee.designationCode="+employee.getDesignationCode()+";");
pw.println("employee.designation=\""+employee.getDesignation()+"\";");
pw.println("employee.dateOfBirth=\""+simpleDateFormat.format(employee.getDateOfBirth())+"\";");
pw.println("employee.gender=\""+employee.getGender()+"\";");
pw.println("employee.isIndian="+employee.getIsIndian()+";");
pw.println("employee.basicSalary="+employee.getBasicSalary().toPlainString()+";");
pw.println("employee.panNumber=\""+employee.getPANNumber()+"\";");
pw.println("employee.aadharCardNumber=\""+employee.getAadharCardNumber()+"\";");
pw.println("employees[i]=employee;");
pw.println("i++;");
}
pw.println("function selectEmployee(row,employeeId)");
pw.println("{");
pw.println("if(selectedRow==row) reutrn;");
pw.println("if(selectedRow!=null)");
pw.println("{");
pw.println("selectedRow.style.background=\"white\";");
pw.println("selectedRow.style.color=\"black\";");
pw.println("}");
pw.println("row.style.background=\"#7C7B7B\";");
pw.println("row.style.color=\"white\";");
pw.println("selectedRow=row;");
pw.println("var i;");
pw.println("for(i=0;i<employees.length;i++)");
pw.println("{");
pw.println("if(employees[i].employeeId==employeeId)");
pw.println("{");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("var emp=employees[i];");
pw.println("document.getElementById(\"detailPanel_employeeId\").innerHTML=emp.employeeId;");
pw.println("document.getElementById(\"detailPanel_name\").innerHTML=emp.name;");
pw.println("document.getElementById(\"detailPanel_designation\").innerHTML=emp.designation;");
pw.println("document.getElementById(\"detailPanel_dateOfBirth\").innerHTML=emp.dateOfBirth;");
pw.println("document.getElementById(\"detailPanel_gender\").innerHTML=emp.gender;");
pw.println("if(emp.isIndian)");
pw.println("{");
pw.println("document.getElementById(\"detailPanel_isIndian\").innerHTML=\"Yes\";");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("document.getElementById(\"detailPanel_isIndian\").innerHTML=\"No\";");
pw.println("}");
pw.println("document.getElementById(\"detailPanel_basicSalary\").innerHTML=emp.basicSalary;");
pw.println("document.getElementById(\"detailPanel_panNumber\").innerHTML=emp.panNumber;");
pw.println("document.getElementById(\"detailPanel_aadharCardNumber\").innerHTML=emp.aadharCardNumber;");
pw.println("}");
pw.println("</script>");
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
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<b>Employees</b><br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div>");
pw.println("<!-- Left panel ends here -->");
pw.println("<!-- Right panel starts here -->");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:105px;height:65vh;border:1px solid black;padding:5px'>");
pw.println("<h4>Employees</h4>");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:5px;height:30vh;border:1px solid black;padding:5px;overflow:scroll'>");
pw.println("<table border='1'>");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding:5px'><a href='/styleone/getEmployeeAddForm'>Add employee</a></th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:60px;text-align:center'>S.No.</th>");
pw.println("<th style='width:200px;text-align:center'>Id</th>");
pw.println("<th style='width:200px;text-align:center'>Name</th>");
pw.println("<th style='width:200px;text-align:center'>Designation</th>");
pw.println("<th style='width:100px;text-align:center'>Edit</th>");
pw.println("<th style='width:100px;text-align:center'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
int sno=0;
for(EmployeeDTO employee:employees)
{
sno++;
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this,\""+employee.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+sno+".</td>");
pw.println("<td>"+employee.getEmployeeId()+"</td>");
pw.println("<td>"+employee.getName()+"</td>");
pw.println("<td>"+employee.getDesignation()+"</td>");
pw.println("<td><a href='/styleone/editEmployee?employeeId="+employee.getEmployeeId()+"'>Edit</a></td>");
pw.println("<td><a href='/styleone/confirmDeleteEmployee?employeeId="+employee.getEmployeeId()+"'>Delete</a></td>");
pw.println("</tr>");
}
pw.println("</tbody>");
pw.println("</table>");
pw.println("</div>");
pw.println("<div style='margin-top:5px;margin-bottom:5px;margin-right:5px;margin-left:5px;height:25vh;border:1px solid black;padding:5px'>");
pw.println("<label style='background:gray;color:white;padding-left:5px;padding-right:5px'>Details</label>");
pw.println("<table border='0' width=\"100%\">");
pw.println("<tr>");
pw.println("<td>Employee id : <span id='detailPanel_employeeId' style='margin-right:30px'></span></td>");
pw.println("<td>Name : <span id='detailPanel_name' style='margin-right:30px'></span></td>");
pw.println("<td>Designation : <span id='detailPanel_designation' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth : <span id='detailPanel_dateOfBirth' style='margin-right:30px'></span></td>");
pw.println("<td>Gender : <span id='detailPanel_gender' style='margin-right:30px'></span></td>");
pw.println("<td>Is indian : <span id='detailPanel_isIndian' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic salary : <span id='detailPanel_basicSalary' style='margin-right:30px'></span></td>");
pw.println("<td>PAN number : <span id='detailPanel_panNumber' style='margin-right:30px'></span></td>");
pw.println("<td>Aadhar card number : <span id='detailPanel_aadharCardNumber' style='margin-right:30px'></span></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div>");
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


}catch(Exception exception)
{
System.out.println(exception);
}
}
}