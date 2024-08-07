document.addEventListener("DOMContentLoaded", () => {
    const userNameInput = document.getElementById("user-name");
    const userEmailInput = document.getElementById("user-email");
    const captchaInput = document.getElementById("captchaInput");
    const loginButton = document.querySelector(".btn-login");
    const loginForm = document.getElementById("login-form");
    const loginBtnText = document.getElementById("login-btn-text");

    // 두 입력 필드의 값을 확인하고 버튼 상태를 업데이트하는 함수
    function checkInputs() {
        if (userNameInput.value.trim() && userEmailInput.value.trim() && captchaInput.value.trim()) {
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
    function validateCaptcha(event) {
        const value = event.target.value;
        const regex = /^[0-9]*$/;
        if (!regex.test(value)) {
            event.target.value = value.replace(/[^0-9]/g, "");
        }
    }

    // 입력 검증 버튼 업데이트
    function validateAndCheckInputs(event) {
        const inputId = event.target.id;

        if (inputId === "user-name") {
            validateUserName(event);
        } else if (inputId === "user-email") {
            validateUserEmail(event);
        } else if (inputId === "captchaInput") {
            validateCaptcha(event);
        }

        checkInputs();
    }

    // 각 입력 필드에 이벤트 리스너 추가
    userNameInput.addEventListener("input", validateAndCheckInputs);
    userEmailInput.addEventListener("input", validateAndCheckInputs);
    captchaInput.addEventListener("input", validateAndCheckInputs);

    // 기본 폼 제출 동작 방지
    loginForm.addEventListener("submit", (event) => {
        event.preventDefault();

        const userNameValue = userNameInput.value.trim();
        const userEmailValue = userEmailInput.value.trim();
        const captchaValue = captchaInput.value.trim();
        const userNameRegex = /^[ㄱ-ㅎ가-힣a-zA-Z]*$/;
        const userEmailRegex = /^[a-zA-Z0-9@.]*$/;
        const isUserNameValid = userNameRegex.test(userNameValue);
        const isUserEmailValid = userEmailRegex.test(userEmailValue);
        const isCaptchaValid = captchaValue === "0587";

        if (!isUserNameValid && !isCaptchaValid) {
            alert("다시 입력해주세요.");
            return;
        }

        if (!isUserNameValid) {
            alert("이름을 확인해주세요.");
            return;
        }

        if (!isUserEmailValid) {
            alert("이메일을 확인해주세요."); // 이메일 유효하지 않은 경우 경고 메시지
            return;
        }

        if (!isCaptchaValid) {
            alert("문자입력이 잘못되었습니다.");
            return;
        }

        // 모든 조건이 일치하면 지정된 URL로 리디렉션
        window.location.href = "#";
    });
});
