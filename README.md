# Delivery

This application represents the process of organizing delivery. <br>
WorkFlow: Shedule a date for delivery -> Add new Client, add/update Product -> Add Order (with quantity) -> Show /Order with detail/Sum of orders etc.

<b>Java</b> is used for backend with freamworks <b>Spring</b> and <b>Hibernate</b>, and in part for frontend <b>JSP</b>.<br>
<b>Cloudinary</b> API for storage images.<br>
App follow a design patter of <b>MVC (Model-View-Controller)</b>. <br>
Controller serve for reciving and response to the <b>HTTP</b> requests. <br>
Model is used for transfering data from database to View  and in reverse from View, to write in database. <br>

Admin panel is created with <b>JavaServer Pages (JSP)</b> that implement <b>Thymeleaf</b> engine and <b>JavaScript (JS) Vanilla</b> for display and functionality of content.<br> <b>Spring Security</b> is implemented and checks each request whether the user is authorized.

There are also <b>REST</b> controllers that work on the same database to return and receive data in <b>JSON</b> format. <br>
This section is designed to be a company presentation that anyone will be able to see. Later potential user can register and make a order.

Developed by Aleksandar Aleksic, 2020
