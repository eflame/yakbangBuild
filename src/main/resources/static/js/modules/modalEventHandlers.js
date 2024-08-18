import * as member from '../admin/member.js';
import * as pill from '../admin/pill.js';


// 수정 버튼
export function handleModifyClick(e) {
    const $btnArea = document.querySelector(".btn-area");
    const $qnaBody = document.querySelector(".qna-body");
    const userId = document.querySelector("#modal-member-id")?.value;
    const itemId = document.querySelector("#modal-item-id")?.value;
    const qnaId = document.querySelector("#modal-qna-id")?.value;
    $btnArea.classList.add("modify");

    // 정보 수정 요청 처리
    if (userId) {
        member.modifyMember(e)
        .catch(error => {
            console.error('회원 정보 수정 중 오류 발생:', error);
            alert('회원 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
        });
    } else if (itemId) {
        pill.modifyMember(e)
        .catch(error => {
            console.error('약 정보 수정 중 오류 발생:', error);
            alert('약 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
        });
    } else if (qnaId) {
        const contentOld = $qnaBody.querySelector('.cont').innerText;
        $qnaBody.innerHTML = `<textarea class="textarea" cols="30" rows="10">${contentOld}</textarea>`;
    }
}

export function handleCheckClick(e) {
    document.querySelector(".modal-wrap").classList.remove("open");
    document.querySelector(".dim")?.remove();
}

// 적용 버튼
export function handleChangeModifyClick(e) {
    const $btnArea = document.querySelector(".btn-area");
    const $qnaBody = document.querySelector(".qna-body");
    const userId = document.querySelector("#modal-member-id")?.value;
    const itemId = document.querySelector("#modal-item-id")?.value;
    const qnaId = document.querySelector("#modal-qna-id")?.value;
    $btnArea.classList.remove("modify");

    if(userId) {
        member.modifyMember(e)
        .catch(error => {
            console.error('회원 정보 수정 중 오류 발생:', error);
            alert('회원 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
        });
    } else if (itemId) {
        pill.modifyMember(e)
            .catch(error => {
                console.error('약 정보 수정 중 오류 발생:', error);
                alert('약 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
            });
    } else if (qnaId) {
        let content = $qnaBody.querySelector('.textarea').value;
        $qnaBody.innerHTML = `<span class="cont">${content}</span>`;
    }
}

export function handleBackClick(e) {
    const $btnArea = document.querySelector(".btn-area");
    const $qnaBody = document.querySelector(".qna-body");
    const userId = document.querySelector("#modal-member-id")?.value;
    const itemId = document.querySelector("#modal-item-id")?.value;
    const qnaId = document.querySelector("#modal-qna-id")?.value;
    $btnArea.classList.remove("modify");

    if(userId) {
        member.modifyMember(e)
            .catch(error => {
                console.error('회원 정보 수정 중 오류 발생:', error);
                alert('회원 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
            });
    } else if (itemId) {
        pill.modifyMember(e)
            .catch(error => {
                console.error('약 정보 수정 중 오류 발생:', error);
                alert('약 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
            });
    } else if (qnaId) {
        let content = $qnaBody.querySelector('.textarea').value;
        $qnaBody.innerHTML = `<span class="cont">${content}</span>`;
    }

}