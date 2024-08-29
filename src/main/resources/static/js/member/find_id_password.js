document.addEventListener("DOMContentLoaded", () => {
    const userNameInput = document.getElementById("user-name");
    const userIdInput = document.getElementById("user-id");
    const userEmailInput = document.getElementById("user-email");
    const submitButton = document.getElementById("submitButton");
    const recaptchaTokenInput = document.getElementById('recaptchaToken');

    submitButton.disabled = true;

    const validationRules = {
        "user-name": /^[가-힣a-zA-Z\s]+$/,
        "user-id": /^[a-zA-Z0-9_]{4,20}$/,
        "user-email": /^[a-zA-Z0-9@.]+$/
    };

    function validateInput(inputElement) {
        const inputValue = inputElement.value.trim();
        const regex = validationRules[inputElement.id];

        if (!regex.test(inputValue)) {
            inputElement.value = inputValue.replace(/[^가-힣a-zA-Z0-9@.]/g, "");
        }
    }

    function checkInputs() {
        const isUserNameValid = validationRules["user-name"].test(userNameInput?.value.trim());
        const isUserIdValid = validationRules["user-id"].test(userIdInput?.value.trim());
        const isUserEmailValid = validationRules["user-email"].test(userEmailInput.value.trim());
        const recaptchaResponse = grecaptcha.getResponse();

        console.log("User Name Valid:", isUserNameValid);
        console.log("User Id Valid:", isUserNameValid);
        console.log("User Email Valid:", isUserEmailValid);
        console.log("reCAPTCHA Token:", recaptchaResponse);

        if(isUserNameValid) {
            submitButton.disabled = !(isUserNameValid && isUserEmailValid && recaptchaResponse);
        } else if (isUserIdValid) {
            submitButton.disabled = !(isUserIdValid && isUserEmailValid && recaptchaResponse);
        }
    }

    userNameInput?.addEventListener("input", () => {
        validateInput(userNameInput);
        checkInputs();
    });
    userIdInput?.addEventListener("input", () => {
        validateInput(userIdInput);
        checkInputs();
    });
    userEmailInput.addEventListener("input", () => {
        validateInput(userEmailInput);
        checkInputs();
    });

    window.recaptchaCallback = function() {
        recaptchaTokenInput.value = grecaptcha.getResponse();
        checkInputs();
    };
});
