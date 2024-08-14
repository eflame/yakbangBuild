import { modifyMember } from '../admin/member.js';

// 수정 버튼
export function handleModifyClick(e) {
    const $btnArea = document.querySelector(".btn-area");
    const $qnaBody = document.querySelector(".qna-body");
    
    $btnArea.classList.add("modify");

    // 정보 수정 요청 처리
    modifyMember(e)
        .catch(error => {
            console.error('회원 정보 수정 중 오류 발생:', error);
            alert('회원 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
        });
    
    // qna 수정
    if ($qnaBody) {
        const contentOld = $qnaBody.querySelector('.cont').innerText;
        $qnaBody.innerHTML = `<textarea class="textarea" cols="30" rows="10">${contentOld}</textarea>`;
    }
}

export function handleCheckClick(e) {
    document.querySelector(".modal-wrap").classList.remove("open");
}

// 적용 버튼
export function handleChangeModifyClick(e) {
    const $btnArea = document.querySelector(".btn-area");
    const $qnaBody = document.querySelector(".qna-body");

    $btnArea.classList.remove("modify");

    modifyMember(e)
        .catch(error => {
            console.error('회원 정보 수정 중 오류 발생:', error);
            alert('회원 정보 수정 중 오류가 발생했습니다. 다시 시도해 주세요.');
        });

    if ($qnaBody) {
        let content = $qnaBody.querySelector('.textarea').value;
        $qnaBody.innerHTML = `<span class="cont">${content}</span>`;
    }
}

export function handleBackClick(e) {
    const $btnArea = document.querySelector(".btn-area");
    const $qnaBody = document.querySelector(".qna-body");

    $btnArea.classList.remove("modify");

    if ($qnaBody) {
        const contentOld = $qnaBody.querySelector('.textarea').value;
        $qnaBody.innerHTML = `<span class="cont">${contentOld}</span>`;
    }
}