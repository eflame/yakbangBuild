{
    // 로그인 폼 제출 이벤트 핸들러
    document.querySelector(".form-area").addEventListener("submit", function (e) {
        let username = document.getElementById("loginId").value;
        let password = document.getElementById("password").value;
        let saveLoginId = document.getElementById("saveLoginId");

        const usernameRegex = /^[a-zA-Z0-9_]{4,20}$/; // 아이디: 영문대소문자, 숫자
        const passwordRegex = /^[a-zA-Z0-9]{4,20}$/; // 비밀번호: 영문대소문자, 숫자

        if (!usernameRegex.test(username)) {
            e.preventDefault();
            alert("아이디를 다시 입력해주세요.");
            window.location.reload();
            return;
        }

        if (!passwordRegex.test(password)) {
            e.preventDefault();
            alert("비밀번호를 다시 입력해주세요.");
            window.location.reload();
            return;
        }

        // 로그인 성공 시 체크박스가 선택된 경우 아이디 값을 저장
        if (saveLoginId.checked) {
            localStorage.setItem("savedUsername", username);
        } else {
            localStorage.removeItem("savedUsername");
        }
    });

// 페이지 로드 시 저장된 아이디를 입력란에 자동 채우기
    document.addEventListener("DOMContentLoaded", function () {
        const savedUsername = localStorage.getItem("savedUsername");
        if (savedUsername) {
            document.getElementById("loginId").value = savedUsername;
            document.getElementById("saveLoginId").checked = true; // 체크박스도 자동으로 체크
        }
    });

}
