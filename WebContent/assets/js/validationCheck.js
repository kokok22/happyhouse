$(document).ready(function(){
	$("#emailwar").hide();
	$("#pwdchkwar").hide();
	$("#pwdwar").hide();
});

function CheckEmail(str){                                                 
     var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
     if(!reg_email.test(str)) {                            
          return false;         
     } else                     
          return true;                                    
}

$("#email").keyup(function(){
	var email = $("#email").val();
	
	if(CheckEmail(email))
		$("#emailwar").hide();
	else
		$("#emailwar").show();
});


$("#userPwd").keyup(function(){
	var v1 = $("#userPwd").val();
	var v2 = $("#userPwdchk").val();
	
	if(v1!=v2)
		$("#pwdchkwar").show();
	else
		$("#pwdchkwar").hide();
	
	if(v1.length < 6)
		$("#pwdwar").show();
	else
		$("#pwdwar").hide();
});

$("#userPwdchk").keyup(function(){
	var v1 = $("#userPwd").val();
	var v2 = $("#userPwdchk").val();
	
	if(v1!=v2)
		$("#pwdchkwar").show();
	else
		$("#pwdchkwar").hide();
});


