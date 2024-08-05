document.addEventListener("DOMContentLoaded", () => {
    const userIdInput = document.getElementById("user-id");
    const captchaInput = document.getElementById("captcha");
    const loginButton = document.querySelector(".btn-login");
    const loginForm = document.getElementById("login-form");
    // 두 입력 필드의 값을 확인하고 버튼 상태를 업데이트하는 함수
    function validateInputs() {
        // 두 입력 필드에 값이 있는지 확인
        if (userIdInput.value.trim() && captchaInput.value.trim()) {
            loginButton.disabled = false; // 버튼 활성화
            loginButton.classList.add("enabled"); // 활성화 클래스 추가
        } else {
            loginButton.disabled = true; // 버튼 비활성화
            loginButton.classList.remove("enabled"); // 활성화 클래스 제거
        }
    }
    // 문자 입력 필드의 유효성을 검사하는 함수 (숫자만 허용)
    function validateCaptcha(event) {
        const value = event.target.value;
        const regex = /^[0-9]*$/; // 숫자만 허용
        if (!regex.test(value)) {
            event.target.value = value.replace(/[^0-9]/g, ""); // 유효하지 않은 문자 제거
        }
    }
    // 입력 필드에 값이 변경될 때마다 validateInputs 함수를 호출
    userIdInput.addEventListener("input", validateInputs);
    captchaInput.addEventListener("input", validateInputs);
    // 문자 입력 필드의 유효성을 검사하는 이벤트 리스너
    captchaInput.addEventListener("input", validateCaptcha);
    // 폼 제출 시 호출되는 이벤트 핸들러
    loginForm.addEventListener("submit", (event) => {
        event.preventDefault(); // 기본 폼 제출 동작 방지
        const userIdValue = userIdInput.value.trim();
        const captchaValue = captchaInput.value.trim();
        // 아이디 유효성 검사
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
