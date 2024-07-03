// 이메일 유효성 검사
const userEmail = document.getElementById("userEmail");
const emailReg =
  /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;
const emailCheck = document.getElementsByClassName("emailCheck");
userEmail.oninput = () => {
  //email 유효성 검사는 여기서
  if (emailReg.test(userEmail.value)) {
    emailCheck[0].classList.add("hidden");
    userEmail.classList.remove("disabled");
    $("#userEmail").parent('div').addClass("EmailTextField_sucess");
    //console.log("성공");
  } else {
    emailCheck[0].classList.remove("hidden");
    emailCheck[0].textContent = "이메일 주소가 맞나요?";
    userEmail.classList.add("disabled");
    $("#userEmail").parent('div').removeClass("EmailTextField_sucess");
    if (userEmail.value == "") {
      emailCheck[0].textContent = "꼭 필요해요";
      userEmail.classList.add("disabled");
      $("#userEmail").parent('div').removeClass("EmailTextField_sucess");
    }
  }
};


// 이름 유효성 검사
const userName = document.getElementById("userName");
const nameCheck = document.getElementsByClassName("nameCheck");
userName.oninput = () =>{
	if(document.getElementById("userName").value.length >= 2){
		nameCheck[0].classList.add("hidden");
		nameCheck[0].textContent = "";
		userName.classList.remove("disabled");
		$("#userName").parent('div').addClass("EmailTextField_sucess");
	}
	else{
		nameCheck[0].classList.remove("hidden");
		nameCheck[0].textContent="이름은 두 글자 이상 입력해주세요.";
		userName.classList.add("disabled");
		$("#userName").parent('div').removeClass("EmailTextField_sucess");
		
		if(document.getElementById("userName").value == ""){
			nameCheck[0].textContent="멋진 이름이 있으시잖아요.";
			userName.classList.add("disabled");
			$("#userName").parent('div').removeClass("EmailTextField_sucess");
		}
	}
}
//비밀번호 유효성
const userPassword = document.getElementById("userPassword");
const pwCheck = document.getElementsByClassName("pwCheck");
userPassword.oninput = () =>{
	let reg = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?=[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{6,15}$/;
	if(reg.test(userPassword.value)){
		pwCheck[0].classList.add("hidden");
		pwCheck[0].textContent = "";
		userPassword.classList.remove("disabled");
		$("#userPassword").parent('div').addClass("EmailTextField_sucess");
	}
	else{
		pwCheck[0].classList.remove("hidden");
		pwCheck[0].textContent = "비밀번호가 너무 짧습니다.(6자 이상 15자 이하)";
		userPassword.classList.add("disabled");
		$("#userPassword").parent('div').removeClass("EmailTextField_sucess");
	if(userPassword.value == ""){
		pwCheck[0].textContent = "비밀번호를 입력해주세요.";
		userPassword.classList.add("disabled");
		$("#userPassword").parent('div').removeClass("EmailTextField_sucess");
		}
	}
}
//비밀번호 체크 유효성
const userPasswordConfirm = document.getElementById("userPasswordConfirm");
const pwChecked = document.getElementsByClassName("pwChecked");
userPasswordConfirm.oninput=()=>{
	if(document.getElementById("userPasswordConfirm").value == userPassword.value){
		pwChecked[0].classList.add("hidden");
		pwChecked[0].textContent = "";
		userPasswordConfirm.classList.remove("disabled");
		$("#userPasswordConfirm").parent('div').addClass("EmailTextField_sucess");
	}
	else{
		pwChecked[0].classList.remove("hidden");
		pwChecked[0].textContent = "비밀번호가 일치하지 않습니다.";
		userPasswordConfirm.classList.add("disabled");
		$("#userPasswordConfirm").parent('div').removeClass("EmailTextField_sucess");
	if(userPasswordConfirm.value == ""){
		pwChecked[0].textContent = "비밀번호를 다시 한번 입력해주세요.";
		userPasswordConfirm.classList.add("disabled");
		$("#userPasswordConfirm").parent('div').removeClass("EmailTextField_sucess");
		}
	}
}

//jquery
$(document).ready(function() {
	$("#agreeAll").click(function() {
		// prop으로 전달하면 같은 class의 checked를 활성화, 비활성화 할 수 있다.
		if($("#agreeAll").is(":checked")) $(".Checkbox_input").prop("checked",true);
		else $(".Checkbox_input").prop("checked",false);
	});
	//전체 선택버튼을 누르면 회원가입 disabled를 false로 하여 활성화함.
	$("#agreeAll").click(function(){
		if($("#agreeAll").is(":checked")) $(".undefind").prop("disabled",false);
		else $(".undefind").prop("disabled",true);
	});
	
	//아래 자식 항목의 체크한 갯수와 자식항목의 갯수가 같다면 전체항목체크를 활성화하고 아니면 활성화 하지 않음.
	$(".Checkbox_input").click(function() {
		const total = $(".Checkbox_input").length;
		const checked = $(".Checkbox_input:checked").length;
		if(total != checked) $("#agreeAll").prop("checked", false);
		else $("#agreeAll").prop("checked",true); 
	});
	
	$(".c_h").click(function(){
		//.c_h의 갯수랑 체크된 항목의 갯수와 같다면 모두 선택한거임. 선택빼고, 필수에는 .c_h 클래스이름 지정.
		const tott = $(".c_h").length;
		const check = $(".c_h:checked").length;
		//undefind는 회원가입 버튼의 클래스. 필수 선택을 다하면 disabled를  false로 하였다.
		if(tott == check) $(".undefind").prop("disabled",false);
		// 그 외는 버튼을 비활성화
		else $(".undefind").prop("disabled",true);
	});
});