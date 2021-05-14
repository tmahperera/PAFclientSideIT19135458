$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

///SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validatePromotionForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidpromotionIDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "promotionservelet",
		type : t,
		data : $("#formPromotion").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onResearchSaveComplete(response.responseText, status);
		}
	});
}); 

function onResearchSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidpromotionIDSave").val("");
	$("#formPromotion")[0].reset();
}


//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidpromotionIDSave").val($(this).closest("tr").find('#hidpromotionIDUpdate').val());     
	$("#promotionName").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#promotionType").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#discountPercentage").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#startDate").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#endDate").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});

//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "promotionservelet",
		type : "DELETE",
		data : "promotionID=" + $(this).data("promotionid"),
		dataType : "text",
		complete : function(response, status)
		{
			onpromotionDeletedComplete(response.responseText, status);
		}
	});
});

function onPromotionDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validatePromotionForm() {  
	// NAME  
	if ($("#promotionName").val().trim() == "")  {   
		return "Insert  Promotion Name.";  
		
	} 
	
	 // Type 
	if ($("#promotionType").val().trim() == "")  {   
		return "Insert Promotion Type.";  
		
	} 
	 
	
	// Discount  
	if ($("#discountPercentage").val().trim() == "")  {   
		return "Insert Discount Percentage.";  
		
	}  
	
	
	// start Date  
	if ($("#startDate").val().trim() == "")  {   
		return "Insert Start Date.";  
		
	} 
	
	// end Date  
	if ($("#endDate").val().trim() == "")  {   
		return "Insert End Date.";  
		
	} 
	
	
		 
	 return true; 
	 
}
