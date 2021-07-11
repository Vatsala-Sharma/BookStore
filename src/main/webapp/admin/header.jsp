<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}/admin/">
			<img
			src="http://localhost:8081/BookStoreWebsite/images/BookstoreAdminLogo.png" />
		</a>	
	</div>
	<div>
		Welcome, <c:out value="${sessionScope.useremail}"></c:out> | <a href="logout">Logout</a>
		<br/><br/>
	</div>
	<div id="headermenu">
			<div>
			 	<a href="list_users">
			 		<img src="../images/userIcon.png" width="60" height="60"/><br />Users</a> |
			 </div> 
			 <div>
			 		<a href="list_category">
			 		<img src="../images/category.png" width="60" height="60" /><br/>Category</a> |
			 </div>
			 <div>
			 	<a href="list_books">
			 	<img src="../images/books.png" width="60" height="60" /><br />Books</a> |
			 </div>
			 <div>
			 	<a href="list_customer">
			 	<img src="../images/customer.png" width="60" height="60" /><br />Customers</a> |
			 </div>
			 <div>
			 	<a href="list_review">
			 	<img src="../images/review.png" width="60" height="60" /><br />Reviews</a> |
			 </div>
			 <div>
			 	<a href="list_order">
			 	<img src="../images/carts.png" width="60" height="60" /><br />Orders</a> 
			 </div>
	</div>
</div>