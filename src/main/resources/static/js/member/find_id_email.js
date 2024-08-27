document.addEventListener("DOMContentLoaded", () => {
    const userNameInput = document.getElementById("user-name");
    const userEmailInput = document.getElementById("user-email");
    const submitButton = document.getElementById("btn-check");

    // 유효성 검사 규칙
    const validationRules = {
        "user-name": /^[ㄱ-ㅎ가-힣a-zA-Z]*$/,
        "user-email": /^[a-zA-Z0-9@.]*$/
    };

    function validateInput(inputElement) {
        const inputValue = inputElement.value.trim();
        const regex = validationRules[inputElement.id];

        if (!regex.test(inputValue)) {
            inputElement.value = inputValue.replace(/[^ㄱ-ㅎ가-힣a-zA-Z0-9@.]/g, "");
        }
    }

    function checkInputs() {
        const isUserNameValid = validationRules["user-name"].test(userNameInput.value.trim());
        const isUserEmailValid = validationRules["user-email"].test(userEmailInput.value.trim());

        // 모든 필드가 유효하면 버튼 활성화
        submitButton.disabled = !(isUserNameValid && isUserEmailValid && window.recaptchaToken);
    }

    function handleInput(event) {
        validateInput(event.target);
        checkInputs();
    }

    userNameInput.addEventListener("input", handleInput);
    userEmailInput.addEventListener("input", handleInput);

    // reCAPTCHA v3 준비 및 토큰 발급
    grecaptcha.ready(() => {
        grecaptcha.execute('6LflFS8qAAAAAKV_ANEzLehO8asihLHfcO41z1wO', {action: 'submit'}).then((token) => {
            window.recaptchaToken = token; // 전역 변수에 토큰 저장
            checkInputs(); // 토큰이 준비되면 입력 확인
        });
    });

    // 폼 제출 시 reCAPTCHA 검증
    document.getElementById("find-id-email").addEventListener("submit", (event) => {
        // reCAPTCHA 토큰이 없으면 제출을 방지
        if (!window.recaptchaToken) {
            event.preventDefault();
            alert("자동 입력 방지문자를 확인해주세요.");
        }
    });
});
