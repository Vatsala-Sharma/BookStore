<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write a Review</title>
	<link rel="stylesheet" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>

	<jsp:directive.include file="header.jsp" />
		<div align="center">
			<form id="reviewForm" action="submit_review" method="post">
				<table class="normal" width="60%">
					<tr>
						<td>
							<h2>Your Reviews</h2>								
						</td>	
						<td>&nbsp;</td>			
						<td><h2>${loggedCustomer.fullname}</h2></td>	
					</tr>
					<tr>
							<td colspan="3"><hr/></td>
					</tr>
					<tr>
					<td>
						<span id="book-title">${book.title}</span><br/>
						<img class="book-large" src="data:image/jpg;base64,${book.base64Image}" />
					</td>
					<td>
						<div id="rateYo">
							<input type="hidden" id="rating" name="rating" />
							<input type="hidden" name="bookId" value="${book.bookId}"/>
						</div>
						<br/>
						<input type="text" name="headline" size="60" placeholder="Headline or summary for your review(required)" />
						<br/>
						<br/>
						<textarea name="comment" cols="70" rows="10" placeholder="Write your review details..."></textarea>
					</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<button type="submit">Submit</button>
							&nbsp;&nbsp;
							<button id="buttonCancel">Cancel</button>
						</td>
					</tr>
				</table>			
			</form>
		</div>
	<jsp:directive.include file="footer.jsp" />
</body>

<script type="text/javascript">

		$(document).ready(function(){
			$("#reviewForm").validate({
				rules:{
					headline: "required",
					comment: "required"	
		
				},
				messages:{
					headline: "Please write headline",
						
					comment: "Please enter comment"
				}
			});
			 $("#rateYo").rateYo({
				    starWidth: "40px",
				    fullStar: true,
				    onSet: function(rating, rateYoInstance){
				    	$("#rating").val(rating);
				    }
				  });
			 $("#buttonCancel").click(function(){
					history.go(-1);
				});
		});
</script>

</html>