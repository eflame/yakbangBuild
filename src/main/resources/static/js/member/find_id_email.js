document.addEventListener("DOMContentLoaded", () => {
    const userNameInput = document.getElementById("user-name");
    const userEmailInput = document.getElementById("user-email");
    const submitButton = document.getElementById("btn-check");

    // 유효성 검사 함수
    const validationRules = {
        "user-name": /^[ㄱ-ㅎ가-힣a-zA-Z]*$/,
        "user-email": /^[a-zA-Z0-9@.]*$/
    };

    function validateInput(inputElement) {
        const inputValue = inputElement.value.trim();
        const regex = validationRules[inputElement.id];

        // 정규식을 사용해 유효성 검사 수행
        if (!regex.test(inputValue)) {
            inputElement.value = inputValue.replace(/[^ㄱ-ㅎ가-힣a-zA-Z0-9@.]/g, "");
        }
    }

    function checkInputs() {
        const isUserNameValid = validationRules["user-name"].test(userNameInput.value.trim());
        const isUserEmailValid = validationRules["user-email"].test(userEmailInput.value.trim());
        const captchaResponse = grecaptcha.getResponse();

        // 모든 필드가 유효하면 버튼 활성화
        submitButton.disabled = !(isUserNameValid && isUserEmailValid && captchaResponse);
    }

    function handleInput(event) {
        validateInput(event.target);
        checkInputs();
    }

    // 입력 필드에 이벤트 리스너 추가
    userNameInput.addEventListener("input", handleInput);
    userEmailInput.addEventListener("input", handleInput);

    // reCAPTCHA 준비 및 콜백 설정
    grecaptcha.ready(() => {
        grecaptcha.render('g-recaptcha', {
            'sitekey': '6LflFS8qAAAAAKV_ANEzLehO8asihLHfcO41z1wO',
            'callback': () => {
                checkInputs(); // reCAPTCHA 완료 후 유효성 검사
            }
        });
    });

    // 폼 제출 시 reCAPTCHA 검증
    document.getElementById("login-form").addEventListener("submit", (event) => {
        if (!grecaptcha.getResponse()) {
            event.preventDefault();
            alert("자동 입력 방지문자를 확인해주세요.");
        }
    });
});
