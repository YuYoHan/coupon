// 요소에 마우스를 올리면 첫번째 이미지는 hidden이 되고, 마우스가 벗어나면 두번째 이미지가 hidden이 되어야 한다.
const iconImgWrap = document.getElementsByClassName("icon-wrapper");

for (const icon of iconImgWrap) {
  icon.onclick = function (e) {
    //console.log(e.currentTarget.children[2].value);
    logged(e.currentTarget.children[2].value);
  };
  const iconImg = icon.children[0];
  const iconImgHover = icon.children[1];
  iconImgHover.classList.add("hidden");
  icon.addEventListener("mouseenter", () => {
    iconImg.classList.add("hidden");
    iconImgHover.classList.remove("hidden");
  });
  icon.addEventListener("mouseleave", () => {
    iconImgHover.classList.add("hidden");
    iconImg.classList.remove("hidden");
  });
}

const logged = (logginMethod) => {
  //로그인 메소드에 따라 case를 다르게 하여 접근할 것임.
	let url ="";
	let newUrl = "";
  switch (logginMethod) {
    case "이메일 회원가입":
      //이메일 회원가입을 요청하는 url
    	 url = location.href //http://localhost:9090/myrealtrip/user/sign_in.us
        //원본은 그대로, 새로운 문자열을 반환.
         newUrl = url.replace("/user/sign_up.us", "/app/user/sign_up_form.jsp");
        //console.log(newUrl);
        location.href = newUrl;
      console.log("이메일로 회원가입 합니다.");
      break;
    case "이메일 로그인":
    	//이메일 로그인을 요청하는 url
         url = location.href //http://localhost:9090/myrealtrip/user/sign_in.us
        //원본은 그대로, 새로운 문자열을 반환.
         newUrl = url.replace("/user/sign_in.us", "/app/user/sign_in_form.jsp");
        //console.log(newUrl);
        location.href = newUrl;
        break;
    case "페이스북":
      //페이스북 회원가입을 요청하는 url
      console.log("페이스북으로 로그인/회원가입 합니다.");

      break;
    case "네이버":
      //네이버 회원가입을 요청하는 url
      console.log("네이버로 로그인/회원가입 합니다.");

      break;
  }
};

window.Kakao.init('e1a0323986af7e5191ce08e46251fdcb'); // javascript 앱키 적는 곳

function kakaoLogin() {
    window.Kakao.Auth.login({
        scope: 'profile_nickname, profile_image, account_email, gender, age_range, birthday', //동의항목 페이지에 있는 개인정보 보호 테이블의 활성화된 ID값을 넣습니다.
        success: function(response) {
            console.log(response) // 로그인 성공하면 받아오는 데이터
            window.Kakao.API.request({ // 사용자 정보 가져오기 
                url: '/v2/user/me',
                success: (res) => {
                    const kakao_account = res.kakao_account;
                    console.log(kakao_account)
                }
            });
            // window.location.href='/ex/kakao_login.html' //리다이렉트 되는 코드
        },
        fail: function(error) {
            console.log(error);
        }
    });
}

