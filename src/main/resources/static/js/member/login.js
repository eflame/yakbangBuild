{
    document.addEventListener("DOMContentLoaded", () => {
        const usernameInput = document.getElementById("username");
        const passwordInput = document.getElementById("password");
        const saveLoginCheckbox = document.getElementById("save-login");

        // 로그인 탭 버튼 이벤트 설정
        document.querySelectorAll(".tab_member_type button").forEach((button) => {
            button.addEventListener("click", (e) => {
                document.querySelectorAll(".tab_member_type button").forEach((btn) => {
                    btn.setAttribute("aria-selected", "false");
                    btn.setAttribute("tabindex", "-1");
                    btn.style.fontSize = "16px";
                });

                // 클릭된 버튼 설정
                e.target.setAttribute("aria-selected", "true");
                e.target.setAttribute("tabindex", "0");
                e.target.style.fontSize = "20px";

                // input hidden value 변경
                document.getElementById("login_tab").value =
                    e.target.getAttribute("data-value");
            });
        });

        // 페이지 로드 시 저장된 아이디 값을 불러오기
        // const savedUsername = localStorage.getItem("savedUsername");
        // if (savedUsername) {
        //     usernameInput.value = savedUsername;
        //     saveLoginCheckbox.checked = true;
        // }

        // 로그인 폼 제출 이벤트 핸들러
        document.querySelector(".form-area").addEventListener("submit", function (e) {
            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;

            console.log(username,password);

            const usernameRegex = /^[a-zA-Z0-9_]{4,20}$/; // 아이디: 영문대소, 숫자
            const passwordRegex = /^[a-zA-Z0-9]{4,20}$/; // 비밀번호: 영문대소, 숫자

            // if (!usernameRegex.test(username)) {
            //     e.preventDefault();
            //     alert("아이디를 다시 입력해주세요.");
            //     // 현재 URL 리로드
            //     window.location.reload();
            //     return;
            // }
            //
            // if (!passwordRegex.test(password)) {
            //     e.preventDefault();
            //     alert("비밀번호를 다시 입력해주세요.");
            //     // 현재 URL 리로드
            //     window.location.reload();
            //     return;
            // }

            // 로그인 성공 시 체크박스가 선택된 경우 아이디 값을 저장
            // if (saveLoginCheckbox.checked) {
            //     localStorage.setItem("savedUsername", username);
            // } else {
            //     localStorage.removeItem("savedUsername");
            // }
        });
    });
}
