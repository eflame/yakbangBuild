document.addEventListener("DOMContentLoaded", () => {
    const userIdInput = document.getElementById("user-id");
    const captchaInput = document.getElementById("captcha");
    const loginButton = document.querySelector(".btn-login");
    const loginForm = document.getElementById("login-form");
    const loginBtnText = document.getElementById("login-btn-text");

    userIdInput.addEventListener("input", checkInputs);
    captchaInput.addEventListener("input", checkInputs);


    // 두 입력 필드의 값을 확인하고 버튼 상태를 업데이트하는 함수
    function checkInputs() {
        if (userIdInput.value.trim() && captchaInput.value.trim()) {
            loginButton.disabled = false;
            loginButton.classList.add("enabled");
            loginBtnText.classList.add("enabled-text");
            loginBtnText.style.color = "white";
        } else {
            loginButton.disabled = true;
            loginButton.classList.remove("enabled");
            loginBtnText.classList.add("enabled-text");
        }
    }


    // 문자 입력 필드의 유효성을 검사하는 함수 (숫자만 허용)
    function validateCaptcha(event) {
        const value = event.target.value;
        const regex = /^[0-9]*$/; // 숫자만 허용
        if (!regex.test(value)) {
            event.target.value = value.replace(/[^0-9]/g, "");
        }
    }

    userIdInput.addEventListener("input", checkInputs);
    captchaInput.addEventListener("input", checkInputs);
    captchaInput.addEventListener("input", validateCaptcha);
    loginForm.addEventListener("submit", (event) => {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        const userIdValue = userIdInput.value.trim();
        const captchaValue = captchaInput.value.trim();
        const userIdRegex = /^[a-zA-Z0-9]*$/;
        const isUserIdValid = userIdRegex.test(userIdValue);
        const isCaptchaValid = captchaValue === "0587";

        if (!isUserIdValid && !isCaptchaValid) {
            alert("다시 입력해주세요.");
            return;
        }

        if (!isUserIdValid) {
            alert("아이디를 확인해주세요.");
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