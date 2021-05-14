<%@page import="model.Promotions"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Promotion Management</title>
<link rel="stylesheet" href = "Views/bootstrap.min.css">
<script src="components/jquery-3.6.0.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Promotions Management</h1>        
				
				<form id="formPromotion" name="formPromotion" method="post" action="promotions.jsp">  
					Promotion Name:  
					<input id="promotionName" name="promotionName" type="text" class="form-control form-control-sm">  
					
					<br> 
					Promotion Type:  
					<input id="promotionType" name="promotionType" type="text" class="form-control form-control-sm">  
					
					<br>
					 Promotion Discount Percentage:  
					 <input id="promotionDiscounts" name="promotionDiscounts" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Promotion Start Date:  
					 <input id="promostart" name="promostart" type="text" class="form-control form-control-sm">
					 
					 <br> 
					 Promotion End Date:  
					 <input id="promoend" name="promoend" type="text" class="form-control form-control-sm">   
					 
				
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidPromotionIDSave" name="hidPromotionIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="#divItemsGrid">   
					<%
   					Promotions rObj = new Promotions();
   									out.print(rObj.readPromotions());
   					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 
</body>
</html>