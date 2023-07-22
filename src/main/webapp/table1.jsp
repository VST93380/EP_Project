<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Employee" %>
<html>
<head>
    <title>StudentDetails Table</title>
   <style>
  body {
      background-color: #000000;
      margin: 0;
      padding: 20px;
  }

  h1 {
      text-align: center;
      color: #333333;
  }
  .table-container {
      max-width: 800px;
      margin: 0 auto;
      
      background-color: transparent; /* Set the background color to transparent */
      padding: 20px;
  }

  table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
      border-radius: 10px;
      border-spacing: 0;
      overflow: hidden;
      position: relative;
      background-color: transparent; /* Set the table background color to transparent */
        box-shadow: 0px 0px 10px #0ef3ef,
             0px 0px 20px  #0ef3ef,
              0px 0px 30px  #0ef3ef,
              0px 0px 40px  #0ef3ef,
              0px 0px 50px  #0ef3ef;
}

  table:before {
      content: "";
      position: absolute;
      top: -5px;
      left: -5px;
      right: -5px;
      bottom: -5px;
      background: linear-gradient(45deg, #ff00ff, #00ffff, #ffff00, #ff00ff); /* Neon colors for the shadow */
      z-index: -1;
      filter: blur(20px);
box-shadow: rgba(14, 146, 152, 0.56) 0px 22px 70px 4px;
  }

  th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #dddddd;
  }

 /* Set the font styles and alignment */
th {
  font-weight: bold;
  text-align: left;
}

/* Set the background color and padding */
th {
  background-color: #f2f2f2;
  padding: 8px;
}

/* Add a border and set border color */
th {
  border-bottom: 2px solid #ddd;
}

/* Hover effect */
th:hover {
  background-color: #e6e6e6;
}

/* Set font color */
th {
  color: #333;
}

  tr:nth-child(even) {
      background-color: #f9f9f9;
  }

  tr:nth-child(odd) {
      background-color: #f1f1f1;
  }

  img {
      max-width: 100px;
      max-height: 100px;
  }
  .button1 {
display: inline-block;
padding: 10px 20px; 
border-radius: 20px;
background-color: #ff69b4;
color: #ffffff;
text-align: center;
text-decoration: none;
font-size: 18px;
font-weight: bold;
box-shadow: 0 0 10px #ff69b4;
transition: box-shadow 0.3s;
position: fixed;
top: 20px;
right: 20px;
z-index: 9999;
overflow: hidden;
}

.button1:before, .button1:after {
content: '';
position: absolute;
top: -2px;
left: -2px;
width: 100%;
height: 100%;
background: linear-gradient(45deg, #00ff00, #ffff00, #00ff00, #ffff00);
z-index: -1;
opacity: 0;
transition: opacity 0.3s;
}

.button1:before {
filter: blur(5px);
}

.button1:after {
filter: blur(10px);
}

.button1:hover {
box-shadow: 0 0 20px #ff69b4, 0 0 20px #00ff00;
}

.button1:hover:before, .button1:hover:after {
opacity: 1;
}

@keyframes floating {
0% {
  transform: translateY(0);
}
50% {
  transform: translateY(-10px);
}
100% {
  transform: translateY(0);
}
}
   
</style>

   <marquee  direction="right" height="100px" scrollamount="15">
  <h1 style="color: rgb(162, 17, 162);position:relative;top:12px;">🎀 🎀 🎀 🎀 🎀 Student Details 🎀 🎀 🎀 🎀 🎀 
  </h1></marquee> 
</head>
<body>
      <a href="/EP_PROJECT" class="button1 float">Home</a>

    <div class="table-container">
        <table>
            <tr>
                <th><h3>Student ID</h3></th>
                <th><h3>Student Name</h3></th>
                <th><h3>Student email</h3></th>
				<th><h3>Student backlogs</h3></th>
                 <th><h3>About Student</h3></th>
                 <th><h3>Student Resume</h3></th>
                 
            </tr>
<% if (request.getAttribute("list") != null) { %>
<% for (Employee emp : (List<Employee>) request.getAttribute("list")) { %>
<!-- Your existing loop code here -->
            <td><%= emp.getId() %></td>
                    <td><%= emp.getFullname()%></td>
                    <td><%= emp.getEmail() %></td>
                    <td><%=  emp.getBacklogs()%></td>
                    <td><%=emp.getAboutYourself() %></td>
                    
          <td>
                        <a href="#" onclick="openPdfAndRefresh('<%= Base64.getEncoder().encodeToString(emp.getResume()) %>')">
    View resume
</a>
                        
                    </td>
                    
                    <td>
                        <button onclick="sendHireEmail('<%= emp.getEmail() %>')">Hire</button>
                    </td>
                    
                    
                    
                </tr>
           <% } %>
<% } %>                <tr>
        
        </table>
    </div>
</body>
<script>
    function openPdfAndRefresh(pdfData) {
        // Convert the Base64 PDF data to Uint8Array
        const binaryPdf = atob(pdfData);
        const len = binaryPdf.length;
        const buffer = new Uint8Array(len);
        for (let i = 0; i < len; i++) {
            buffer[i] = binaryPdf.charCodeAt(i);
        }

        // Create a Blob from the Uint8Array
        const blob = new Blob([buffer], { type: 'application/pdf' });

        // Create a URL for the Blob
        const pdfUrl = URL.createObjectURL(blob);

        // Open the PDF in a new tab
        const newTab = window.open(pdfUrl, '_blank');

        // Refresh the new tab after it has been opened
        if (newTab) {
            newTab.addEventListener('load', () => {
                newTab.location.reload();
            });
        }
    }

    function sendHireEmail(email) {
        // AJAX request to send email
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/send_hire_email.jsp', true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    alert('Email sent to ' + email);
                } else {
                    alert('Failed to send email. Please try again.');
                }
            }
        };
        xhr.send('email=' + email);
    }
</script>

</html>
