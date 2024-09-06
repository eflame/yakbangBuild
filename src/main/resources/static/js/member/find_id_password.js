document.addEventListener("DOMContentLoaded", () => {
    const inputName = document.getElementById("user-name");
    const inputEmail = document.getElementById("user-email");
    const inputId = document.getElementById("user-id");
    const submitBtn = document.getElementById("submitButton");
    let isRecaptchaVerified = false;

    // 모든 입력 필드에 동일한 이벤트 리스너를 적용
    [inputName, inputEmail, inputId].forEach(input => {
        if (input) {
            input.addEventListener("keyup", validation);
        }
    });

    window.onRecaptchaSuccess = function() {
        const recaptchaResponse = grecaptcha.getResponse();
        if (recaptchaResponse.length > 0) {
            isRecaptchaVerified = true;
        } else {
            isRecaptchaVerified = false;
        }
        validation();  // recaptcha 결과에 따라 검증
    };

    function validation() {
        const isNameValid = inputName ? inputName.checkValidity() : false;
        const isIdValid = inputId ? inputId.checkValidity() : false;
        const isEmailValid = inputEmail.checkValidity();

        // 이름 또는 ID가 유효하고, 이메일과 Recaptcha가 유효한지 확인
        if ((isNameValid || isIdValid) && isEmailValid && isRecaptchaVerified) {
            submitBtn.disabled = false;
        } else {
            submitBtn.disabled = true;
        }
    }
});