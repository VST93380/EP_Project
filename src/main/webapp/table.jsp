<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Company" %>
<html>
<head>
    <title>Company Table</title>
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
        box-shadow: 0px 0px 10px #00ff00,
             0px 0px 20px #00ff00,
              0px 0px 30px #00ff00,
              0px 0px 40px #00ff00,
              0px 0px 50px #00ff00;
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
  <h1 style="color: rgb(162, 17, 162);position:relative;top:12px;">🎀 🎀 🎀 🎀 🎀 𝔸𝕧𝕒𝕚𝕝𝕒𝕓𝕝𝕖 𝕔𝕠𝕞𝕡𝕒𝕟𝕚𝕖𝕤 𝕗𝕠𝕣 𝕣𝕖𝕔𝕣𝕦𝕚𝕥𝕞𝕖𝕟𝕥🎀 🎀 🎀 🎀 🎀 
  </h1></marquee> 
</head>
<body>
      <a href="/EP_PROJECT" class="button1 float">Home</a>

    <div class="table-container">
        <table>
            <tr>
                <th><h3>Company Name</h3></th>
                <th><h3>Company Photo</h3></th>
                <th><h3>Contact Number</h3></th>
                <th><h3>Contact Details</h3></th>
                <th><h3>Company email</h3></th>
                <th><h3>Relevant branch</h3></th>
            </tr>
            <% for (Company company : (List<Company>) request.getAttribute("recruiters")) { %>
                <tr>
                    <td><%= company.getName() %></td>
                    <td><img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(company.getImage()) %>"></td>
                    <td><%= company.getContact() %></td>
                    <td><%= company.getDetails() %></td>
                    <td><%= company.getEmail() %></td>
                    <td><%= company.getBranch() %></td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
