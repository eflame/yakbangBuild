let modal = document.getElementById("myModal");
let openModalBtn = document.querySelector(".btn-open-modal");

// 모달을 닫는 함수
function closeModal() {
    modal.style.display = "none"; // 모달 숨기기
    resetModal(); // 모달 상태 초기화
}

// 모달을 여는 함수
function openModal() {
    modal.style.display = "block"; // 모달 표시
}
// 탈퇴 버튼 클릭 시 호출되는 함수
function completeWithdrawal() {
    document.getElementById("completionMessage").style.display = "block"; // 완료 메시지 표시
    document.getElementById("btn-modify").style.display = "none"; // 탈퇴 버튼 숨기기
    document.getElementById("modal-in").style.display = "none"; // 기존 내용 숨기기

}
// 모달을 닫을 때 상태를 초기화하는 함수
function resetModal(){
    document.getElementById("completionMessage").style.display = "none"; // 완료 메시지 표시
    document.getElementById("btn-modify").style.display = "block"; // 탈퇴 버튼 숨기기
    document.getElementById("modal-in").style.display = "block"; // 기존 내용 숨기기
}



{ // 모달 열고 닫기
    openModalBtn.onclick = function (event) {
        event.preventDefault(); // 기본 링크 동작 방지
        openModal();
    }

    // 모달을 닫는 버튼 클릭 시 모달 닫기 및 상태 초기화
    document.querySelector(".btn-close").onclick = function() {
        closeModal();
        resetModal(); // 모달 상태 초기화
    }
    document.getElementById("btn-check").onclick = function() {
        closeModal();
        resetModal(); // 모달 상태 초기화
    }
}