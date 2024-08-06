// ==========================전역 변수/함수==========================

// 기본 입력 필드
const idInput = document.querySelector("#id");
const pwInput = document.querySelector("#password");
const nameInput = document.querySelector("#name");
const birthInput = document.querySelector("#birth");
const phoneInput = document.querySelector("#phone");
const emailInput = document.querySelector("#email");

// 전문가 입력 필드
const managerInput = document.querySelector("#manager-input");
const managerJob = document.querySelector("#job");
const managerAddress1 = document.querySelector("#address");

// 가입 버튼
const joinButton = document.querySelector("#join-button");

const basicInputs = [idInput, pwInput, nameInput, birthInput, phoneInput, emailInput];
const managerInputs = [managerJob, managerAddress1];
let allInputs = basicInputs.concat(managerInputs);

// 모든 input에 keyup 이벤트 발생시 가입버튼 활성화 체크
allInputs.forEach(input => {
  if(input) {
    input.addEventListener('keyup', () => { activeEventCheck(); });
  }
});

// 가입버튼 활성화 체크 함수
function activeEventCheck() {
  const managerInput = document.querySelector("#manager-input");
  const joinButton = document.querySelector("#join-button");

  let allBasicInput = basicInputs.every(input => input.value.trim() !== "");
  let allManagerInput = managerInputs.every(input => input.value.trim() !== "");

  console.log(allBasicInput);
  console.log(allManagerInput);

  if (managerInput.style.display === "block") {
    // 전문가 input이 보일 때 모든 필드가 채워져 있어야 함
    joinButton.disabled = !(allBasicInput && allManagerInput);  
  } else {
    // 전문가 input이 숨겨져 있을 때 기본 필드만 채워져 있으면 됨
    joinButton.disabled = !allBasicInput;
  }
}

// 에러 메세지 색갈 변경
function setErrorStyles(messageElement, inputElement, message) {
  messageElement.textContent = message;
  messageElement.style.color = 'red';
  inputElement.style.borderColor = 'red';
}

function setSuccessStyles(messageElement, inputElement, message) {
  messageElement.textContent = message || "";
  messageElement.style.color = 'green';
  inputElement.style.borderColor = 'green';
}


//========================================================================


{ // ======= 일반 정보 유효성 검사 =======
  // 아이디 유효성 검사
  // 아이디 정규 표현식
  let idReg = /^[a-zA-Z0-9_]{4,20}$/;

  let $idMsgSpan = document.querySelector("#idCheckMsg1 .join-important");

  idInput.addEventListener("blur", function () {
    let value = this.value;

    if (idReg.test(value)) {
      console.log("아이디 통과");
      setSuccessStyles($idMsgSpan, idInput, "사용 가능한 아이디입니다.");
    } else {
      console.log("아이디 다시 입력!");
      setErrorStyles($idMsgSpan, idInput, "4~20자의 영문, 숫자와 특수문자'_'만 사용해주세요.");
    }
  });

  // 비밀번호
  // 비밀번호 정규 표현식
  let pwReg = /^.{4,16}$/;

  let $pwMsg = document.querySelector("#idCheckMsg2 .join-important");

  pwInput.addEventListener("blur", function () {
    let value = this.value;

    if (pwReg.test(value)) {
      console.log("비밀번호 통과");
      setSuccessStyles($pwMsg, pwInput, "사용 가능한 비밀번호입니다.");
    } else {
      console.log("비밀번호 다시 입력!");
      setErrorStyles($pwMsg, pwInput, "4~16자리로 만들어주세요.");
    }
  });

  // 이름
  let $nameMsg = document.getElementById("name-error-msg");

  nameInput.addEventListener("blur", function () {
    // 이름 유효성 검사 정규식 (한글, 영문 대소문자, 공백 포함)
    let namePattern = /^[가-힣a-zA-Z\s]+$/;
    let value = nameInput.value.trim();

    if (!value) {
      setErrorStyles($nameMsg, nameInput, "이름은 필수 입력 정보입니다.");
    } else if (!namePattern.test(value)) {
      setErrorStyles($nameMsg, nameInput, "이름은 한글, 영문 대소문자, 공백만 포함할 수 있습니다.");
    } else {
      setSuccessStyles($nameMsg, nameInput);
    }
  });

  // 전화번호
  let $phoneMsg = document.getElementById("phone-error-msg");

  phoneInput.addEventListener("blur", function () {
    // 010으로 시작하는 11자리 전화번호 유효성 검사 정규식
    let phonePattern = /^010\d{8}$/;
    let value = phoneInput.value.trim();

    if (!value) {
      setErrorStyles($phoneMsg, phoneInput, "전화번호는 필수 입력 정보입니다.");
    } else if (!phonePattern.test(value)) {
      setErrorStyles($phoneMsg, phoneInput, "전화번호는 010으로 시작하고 11자리 숫자여야 합니다.");
    } else {
      setSuccessStyles($phoneMsg, phoneInput);
    }
  });

  // 생년월일
  let $birthMsg = document.getElementById("birth-error-msg");

  birthInput.addEventListener("blur", function () {
    let value = birthInput.value.trim();
    let regex = /^\d{8}$/;
    let currentDate = new Date();

    if (!value) {
      setErrorStyles($birthMsg, birthInput, "생년월일은 필수 입력 정보입니다.");
    } else if (!regex.test(value)) {
      setErrorStyles($birthMsg, birthInput, "생년월일은 YYYYMMDD 형식으로 입력해야 합니다.");
    } else {
      let year = parseInt(value.substring(0, 4), 10);
      let month = parseInt(value.substring(4, 6), 10);
      let day = parseInt(value.substring(6, 8), 10);
      let birthDate = new Date(year, month - 1, day);

      if (
        birthDate.getFullYear() !== year ||
        birthDate.getMonth() !== month - 1 ||
        birthDate.getDate() !== day
      ) {
        setErrorStyles($birthMsg, birthInput, "유효한 날짜를 입력해 주세요.");
      } else if (birthDate > currentDate) {
        setErrorStyles($birthMsg, birthInput, "미래의 날짜는 입력할 수 없습니다.");
      } else {
        setSuccessStyles($birthMsg, birthInput);
      }
    }
  });

  // 이메일
  let $emailMsg = document.getElementById("email-error-msg");

  emailInput.addEventListener("blur", function () {
    let value = emailInput.value.trim();
    let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // 이메일 형식에 대한 정규 표현식

    if (!value) {
      setErrorStyles($emailMsg, emailInput, "이메일 주소는 필수 입력 정보입니다.");
    } else if (!regex.test(value)) {
      setErrorStyles($emailMsg, emailInput, "유효한 이메일 주소를 입력해주세요.");
    } else {
      setSuccessStyles($emailMsg, emailInput);
    }
  });

 
}

{ // ============전문가 유효성 검사==============
   
   function joinManagerBtn() {
    // 전문가 input fullBox -> manager-input
    const managerInput = document.getElementById("manager-input");
    const managerJob = document.getElementById("job");
    const managerAddress1 = document.getElementById("address");
    const joinButton = document.getElementById("join-button");

    // 전문가 input 나타내기
    if (managerInput.style.display !== "block") {
      console.log("나와라!!!");
      managerInput.style.display = "block";
      activeEventCheck();
      
    }
    // 전문가 input 숨기기
    else {
      managerInput.style.display = "none";
      activeEventCheck();
    }

    if (
      managerJob.required &&
      managerAddress1.required) {
      managerJob.required = false;
      managerAddress1.required = false;
    } else {
      managerJob.required = true;
      managerAddress1.required = true;
    }
  }

  // 전문가 직업
  let $jobMsg = document.getElementById("job-error-msg");

  managerJob.addEventListener("blur", function () {
    // 직업 유효성 검사 정규식 (한글, 영문 대소문자, 공백, 하이픈, 슬래시 포함)
    let jobPattern = /^[가-힣a-zA-Z\s\-\/]+$/;
    let value = managerJob.value.trim();

    if (!value) {
      setErrorStyles($jobMsg, managerJob, "직업은 필수 입력 정보입니다.")
    } else if (!jobPattern.test(value)) {
      setErrorStyles($jobMsg, managerJob, "직업은 한글, 영문 대소문자, 공백, 하이픈(-), 슬래시(/)만 포함할 수 있습니다.")
    } else {
      setSuccessStyles($jobMsg, managerJob);
    }
  });
}

{ // =============주소 찾기 처리=================
  let $findAddrBtn = document.querySelector("#mng-addr-btn");

  $findAddrBtn.addEventListener("click", findAddress);

  function findAddress() {
    new daum.Postcode({
      oncomplete: function (data) {
        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
  
        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var addr = ""; // 주소 변수
  
        //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === "R") {
          // 사용자가 도로명 주소를 선택했을 경우
          addr = data.roadAddress;
        } else {
          // 사용자가 지번 주소를 선택했을 경우(J)
          addr = data.jibunAddress;
        }
  
        // 주소 정보를 해당 필드에 넣는다.
        document.getElementById("address").value = addr;
        activeEventCheck();
      },
    }).open();
  }
}



