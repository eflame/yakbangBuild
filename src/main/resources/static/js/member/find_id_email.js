

document.addEventListener("DOMContentLoaded", () => {
    const userNameInput = document.getElementById("user-name");
    const userEmailInput = document.getElementById("user-email");
    const loginButton = document.querySelector(".btn-login");
    const loginForm = document.getElementById("login-form");
    const loginBtnText = document.getElementById("login-btn-text");

    // 두 입력 필드의 값을 확인하고 버튼 상태를 업데이트하는 함수
    function checkInputs() {
        if (userNameInput.value.trim() && userEmailInput.value.trim()) {
            loginButton.disabled = false;
            loginButton.classList.add("enabled");
            loginBtnText.classList.add("enabled-text");
            loginBtnText.style.color = "white";
        } else {
            loginButton.disabled = true;
            loginButton.classList.remove("enabled");
            loginBtnText.classList.remove("enabled-text");
            loginBtnText.style.color = "initial";
        }
    }

    // 유효성 검사(한글영문 허용)
    function validateUserName(event) {
        const value = event.target.value;
        const regex = /^[ㄱ-ㅎ가-힣a-zA-Z]*$/;
        if (!regex.test(value)) {
            event.target.value = value.replace(/[^가-힣]/g, "");
        }
    }

    // 유효성 검사(이메일 형식 허용)
    function validateUserEmail(event) {
        const value = event.target.value;
        const regex = /^[a-zA-Z0-9@.]*$/;
        if (!regex.test(value)) {
            event.target.value = value.replace(/[^a-zA-Z0-9@.]/g, "");
        }
    }

    // 유효성 검사(숫자만 허용)
    // function validateCaptcha(event) {
    //     const value = event.target.value;
    //     const regex = /^[0-9]*$/;
    //     if (!regex.test(value)) {
    //         event.target.value = value.replace(/[^0-9]/g, "");
    //     }
    // }

    // 입력 검증 버튼 업데이트
    function validateAndCheckInputs(event) {
        const inputId = event.target.id;

        if (inputId === "user-name") {
            validateUserName(event);
        } else if (inputId === "user-email") {
            validateUserEmail(event);
        }
        // else if (inputId === "captchaInput") {
        //     validateCaptcha(event);
        // }

        checkInputs();
    }

    // 각 입력 필드에 이벤트 리스너 추가
    userNameInput.addEventListener("input", validateAndCheckInputs);
    userEmailInput.addEventListener("input", validateAndCheckInputs);
    // captchaInput.addEventListener("input", validateAndCheckInputs);

    // 기본 폼 제출 동작 방지
    loginForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const userNameValue = userNameInput.value.trim();
        const userEmailValue = userEmailInput.value.trim();
        const captchaResponse = grecaptcha.getResponse(); // reCAPTCHA 응답 가져오기

        const userNameRegex = /^[ㄱ-ㅎ가-힣a-zA-Z]*$/;
        const userEmailRegex = /^[a-zA-Z0-9@.]*$/;
        const isUserNameValid = userNameRegex.test(userNameValue);
        const isUserEmailValid = userEmailRegex.test(userEmailValue);

        if (!isUserNameValid || !isUserEmailValid || !captchaResponse) {
            alert("모든 필드를 올바르게 입력해주세요.");
            return;
        }

        // 모든 조건이 일치하면 폼을 제출합니다.
        // 서버 측에서 CAPTCHA 검증을 진행합니다.
        loginForm.submit();
    });
});
