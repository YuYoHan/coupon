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